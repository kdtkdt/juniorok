package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Company;
import com.juniorok.juniorok.dto.Address;
import com.juniorok.juniorok.dto.Benefit;
import com.juniorok.juniorok.dto.JoinLeave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyRepository {

    void save(Company company);
    void saveAddress(Address address);
    void saveJoinLeave(JoinLeave joinLeave);
    long findIdByName(String name);

    List<Benefit> findAllBenefitTags();
}
