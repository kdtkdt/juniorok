package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Post;
import com.juniorok.juniorok.dto.Benefit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostRepository {
    void save(Post post);

    @Results(id = "benefitResultMap")
    @Arg(column = "id", javaType = Long.class)
    @Arg(column = "name", javaType = String.class)
    @Select("SELECT * FROM benefits")
    List<Benefit> findAllBenefitTags();
}
