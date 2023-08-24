package com.juniorok.juniorok.controller;


import com.juniorok.juniorok.service.PostService;
import com.juniorok.juniorok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/admin")
public class AdminController {

    private final PostService postService;
    private final UserService userService;


    @GetMapping("/admin/main")
    public String adminmain(Model model){


        model.addAttribute("todayDeadlinePosts", postService.todayDeadlinePosts());
        model.addAttribute("recentReports", postService.recentReports());
        model.addAttribute("recentPost", postService.recentPost());
        model.addAttribute("reportremaincount", postService.reportremaincount());
        model.addAttribute("getPostDeadlineToday", postService.getPostDeadlineToday());
        model.addAttribute("getPostStartAt", postService.getPostStartAt());
        model.addAttribute("usercount", userService.getUsersJoinedTodayCount());


        return "admin_main";
    }

    @GetMapping("/adminpost")
    public String showAdminBoard(Model model){

        //10개씩
//            model.addAttribute("posts", postService.getPage(1, 10));
        //전체
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("getRecommendedPosts", postService.getRecommendedPosts());


        return "admin_post";
    }


    //게시글 낱개삭제
    @GetMapping("/admin/deletePost/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        System.out.println(postId);

        try {
            postService.deletePost(postId);
                return ResponseEntity.ok("Post deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the post");
        }
    }




}
