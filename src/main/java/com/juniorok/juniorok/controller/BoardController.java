package com.juniorok.juniorok.controller;

import com.juniorok.juniorok.config.ApiConfig;
import com.juniorok.juniorok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final PostService postService;
    private final ApiConfig apiConfig;

    @GetMapping({"/main", "/"})
    public String showMainBoard(Model model) {
        loadPosts(model);
        return "mainboard";
    }

    @GetMapping({"/temp"})
    public String showTemporalMainBoard(Model model) {
        loadPosts(model);
        return "temporal_main";
    }

    @GetMapping("/mypage")
    public String showMyPage(Model model) {
        loadPosts(model);
        return "mypage";
    }

    private void loadPosts(Model model) {
        model.addAttribute("posts", postService.getPage(1, 6, null, null));
        model.addAttribute("skills", postService.getAllSkills());
        model.addAttribute("kakaoMapAppkey", apiConfig.getKakaoMapAppkey());
    }

}
