package com.juniorok.juniorok.controller;

import com.juniorok.juniorok.domain.User;
import com.juniorok.juniorok.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "mainboard";
    }

    //검색됨
    @GetMapping("/adminusers")
    public String adminShowUsers(Model model,
                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                 @RequestParam(name = "query", defaultValue = "") String query) {
        int pageSize = 5;
        List<User> users;

        if (query.isEmpty()) {
            users = userService.getPagedUsers(pageSize, page);
        } else {
            users = userService.searchAndPagedUsers(query, pageSize, page);
        }

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        int totalUsers;

        if (query.isEmpty()) {
            totalUsers = userService.getTotalUsersCount();
        } else {
            totalUsers = userService.getTotalUsersCount(query);
        }

        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("query", query);

        return "admin_users";
    }

    //유저 낱개삭제
    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable long userId) {
        userService.deleteUserById(userId);
        return "redirect:/user/adminusers";
    }

    //체크해서삭제
    @GetMapping("/bulkDeleteUsers")
    public ResponseEntity<String> bulkDeleteUsers(@RequestParam("ids[]") List<Long> userIds) {
        userService.bulkDeleteUsers(userIds);
        return ResponseEntity.ok("Bulk deletion successful");
    }


    //권한부여
    @GetMapping("/authority")
    public ResponseEntity<String> authority(@RequestParam("ids[]") List<Long> userIds) {
        userService.authority(userIds);
        return ResponseEntity.ok("authority successful");
    }

    //권한삭제
    @GetMapping("/deleteauthority")
    public ResponseEntity<String> deleteauthority(@RequestParam("ids[]") List<Long> userIds) {
        userService.deleteauthority(userIds);
        return ResponseEntity.ok("authority successful");
    }
    

}
