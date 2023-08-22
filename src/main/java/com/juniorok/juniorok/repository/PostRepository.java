package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostRepository {
    void save(Post post);

    List<Post> findPage(int page, int size);
    Post findById(long id);


    //admin-게시글전체불러옴
    List<Post>  getAllPosts();
    //admin-게시글삭제
    void deletePost(long postId);

}
