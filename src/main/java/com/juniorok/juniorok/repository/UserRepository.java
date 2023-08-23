package com.juniorok.juniorok.repository;

import com.juniorok.juniorok.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserRepository {
    Optional<User> findByName(String name);
    void save(User user);

    List<User> viewUser();
    void deleteUserById(long id);

    void bulkDeleteUsers(@Param("userIds") List<Long> userIds);

    void authority(@Param("userIds") List<Long> userIds);

    void deleteauthority(@Param("userIds") List<Long> userIds);


    //기본페이징용
    @Select("SELECT * FROM users ORDER BY id DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<User> getPagedUsers(@Param("offset") int offset, @Param("pageSize") int pageSize);
    

    //검색용
    @Select("SELECT * FROM users WHERE email LIKE CONCAT('%', #{query}, '%') ORDER BY id DESC LIMIT #{pageSize} OFFSET #{offset}")
    List<User> searchAndPagedUsers(@Param("query") String query, @Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM users")
    int getTotalUsersCount();

    @Select("SELECT COUNT(*) FROM users WHERE nickname LIKE CONCAT('%', #{query}, '%')")
    int getTotalUsersCountByQuery(@Param("query") String query);

    //오늘가입자
    int getUsersJoinedTodayCount();

}
