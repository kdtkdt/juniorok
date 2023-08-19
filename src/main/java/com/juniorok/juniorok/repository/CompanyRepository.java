package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface CompanyRepository {

    void save(Company company);
    long findIdByName(String name);
}
