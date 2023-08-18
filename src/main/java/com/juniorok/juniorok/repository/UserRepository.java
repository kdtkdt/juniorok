package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserRepository {
    Optional<User> findByName(String name);
    void save(User user);
}
