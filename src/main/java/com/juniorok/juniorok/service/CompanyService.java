package com.juniorok.juniorok.service;

import com.juniorok.juniorok.domain.Company;
import com.juniorok.juniorok.dto.Address;
import com.juniorok.juniorok.dto.Benefit;
import com.juniorok.juniorok.dto.JoinLeave;
import com.juniorok.juniorok.form.PostForm;
import com.juniorok.juniorok.openapi.NationalPensionApi;
import com.juniorok.juniorok.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final NationalPensionApi nationalPensionApi;

    @Transactional
    public long saveCompany(PostForm postForm) {
        Company company = extractCompanyInfo(postForm);
        long businessNumber = company.getBusinessNumber();
        if (businessNumber > 1_000_000_000) { // 사업자번호는 10자리
            company.setEmployees(nationalPensionApi.getRecentEmployees(postForm.businessNumber(), postForm.companyName()));
            company.setAvgSalary(nationalPensionApi.getRecentAverageSalary(postForm.businessNumber(), postForm.companyName()));
        }
        companyRepository.save(company);
        postForm.benefits().forEach(benefitId -> companyRepository.saveBenefits(company.getId(), Long.getLong(benefitId)));
        long companyId = companyRepository.findIdByName(company.getName());
        company.setId(companyId);
        if (businessNumber > 1_000_000_000) {
            companyRepository.saveAddress(extractAddressInfo(company));
            companyRepository.saveJoinLeave(extractJoinLeaveInfo(company));
        }
        return companyId;
    }

    private Company extractCompanyInfo(PostForm postForm) {
        return Company.builder()
                .name(postForm.companyName())
                .homepage(postForm.homepageUrl().toString())
                .techBlog(postForm.techBlogUrl().toString())
                .developers(postForm.developers())
                .businessNumber(postForm.businessNumber())
                .build();
    }

    private Address extractAddressInfo(Company company) {
        String[] address = nationalPensionApi.getAddress(company.getBusinessNumber(), company.getName()).split("\\s");
        return Address.builder()
                .company(company)
                .state(address[0])
                .city(address[1])
                .others(address[2])
                .build();
    }

    private JoinLeave extractJoinLeaveInfo(Company company) {
        int join = nationalPensionApi.getJoinCount(company.getBusinessNumber(), company.getName());
        int leave = nationalPensionApi.getLeaveCount(company.getBusinessNumber(), company.getName());
        return JoinLeave.builder()
                .company(company)
                .year(nationalPensionApi.getRecentYearAndMonth()/100)
                .month(nationalPensionApi.getRecentYearAndMonth()%100)
                .join(join)
                .leave(leave)
                .build();
    }

    @Transactional(readOnly = true)
    public List<Benefit> getAllBenefitTags() {
        return companyRepository.findAllBenefitTags();
    }

}
