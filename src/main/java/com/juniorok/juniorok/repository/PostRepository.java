package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostRepository {
    void save(Post post);
}
