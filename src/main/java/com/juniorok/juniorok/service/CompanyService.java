package com.juniorok.juniorok.service;

import com.juniorok.juniorok.domain.Company;
import com.juniorok.juniorok.form.PostForm;
import com.juniorok.juniorok.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public long saveCompany(PostForm postForm) {
        companyRepository.save(extractCompanyInfo(postForm));
        return companyRepository.findIdByName(postForm.companyName());
    }

    private Company extractCompanyInfo(PostForm postForm) {
        return Company.builder()
                .name(postForm.companyName())
                .techBlog(postForm.techBlogUrl().toString())
                .employees(postForm.employees())
                .developers(postForm.developers())
                .build();
    }
}
