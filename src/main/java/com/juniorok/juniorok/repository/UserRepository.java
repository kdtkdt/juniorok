package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {
    void save(User user);
}
