package com.juniorok.juniorok.service;

import com.juniorok.juniorok.domain.User;
import com.juniorok.juniorok.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> viewUser() { return userRepository.viewUser(); }


    public void deleteUserById(long id) {
        userRepository.deleteUserById(id);
    }

    public void bulkDeleteUsers(List<Long> userIds) {
        userRepository.bulkDeleteUsers(userIds);
    }


    public void authority(List<Long> userIds) {
        userRepository.authority(userIds);
    }

    public void deleteauthority(List<Long> userIds) {
        userRepository.deleteauthority(userIds);
    }


    public int getTotalUsersCount() {
        return userRepository.getTotalUsersCount();
    }
    
    //기본페이징용
    public List<User> getPagedUsers(int pageSize, int page) {
        int offset = (page - 1) * pageSize;
        return userRepository.getPagedUsers(offset, pageSize);
    }

    //검색용
    public List<User> searchAndPagedUsers(String query, int pageSize, int page) {
        int offset = (page - 1) * pageSize;
        return userRepository.searchAndPagedUsers(query, pageSize, offset);
    }

    public int getTotalUsersCount(String query) {
        return userRepository.getTotalUsersCountByQuery(query);
    }

    //오늘가입자
    public int getUsersJoinedTodayCount(){return userRepository.getUsersJoinedTodayCount();}

}
