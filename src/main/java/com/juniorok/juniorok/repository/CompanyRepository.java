package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Company;
import com.juniorok.juniorok.dto.Benefit;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface CompanyRepository {

    void save(Company company);
    long findIdByName(String name);

    @Results(id = "benefitResultMap")
    @Arg(column = "id", javaType = long.class)
    @Arg(column = "name", javaType = String.class)
    @Select("SELECT * FROM benefits")
    List<Benefit> findAllBenefitTags();
}
