package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.Post;
import com.juniorok.juniorok.dto.Reports;
import com.juniorok.juniorok.dto.Skill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostRepository {
    void save(Post post);

    List<Post> findPage(@Param("page") int page, @Param("size") int size, @Param("keyword") String keyword, @Param("skills") List<String> skills);
    long findIdByPositionAndCompanyId(String position, long companyId);
    Post findById(long id);


    //admin-게시글전체불러옴
    List<Post>  getAllPosts();
    //admin-게시글삭제
    void deletePost(long postId);
    List<Skill> findAllSkills();
    void savePositionSkills(long postId, long skillId);
    //오늘시작공고
    int getPostStartAt();

    //오늘마감공고
    int getPostDeadlineToday();

    //오늘마감공고3건데이터
    List<Post> todayDeadlinePosts();

    //처리가남은 신고건수
    int reportremaincount();

    //최근 신고건3건
    List<Reports> recentReports();

    //최근공고3개
    List<Post> recentPost();


    
}
