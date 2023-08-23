package com.juniorok.juniorok.controller;

import com.juniorok.juniorok.config.ApiConfig;
import com.juniorok.juniorok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainBoardController {

    private final PostService postService;
    private final ApiConfig apiConfig;

    @GetMapping("/main")
    public String showMainBoard(Model model) {
        model.addAttribute("posts", postService.getPage(1, 10, null, null));
        model.addAttribute("skills", postService.getAllSkills());
        model.addAttribute("kakaoMapAppkey", apiConfig.getKakaoMapAppkey());
        return "mainboard";
    }
}