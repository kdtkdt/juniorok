package com.juniorok.juniorok.controller;

import com.juniorok.juniorok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final PostService postService;

    @GetMapping({"/main", "/"})
    public String showMainBoard() {
        return "mainboard";
    }

    @GetMapping("/mypage")
    public String showMyPage(Model model) {
        model.addAttribute("posts", postService.getPage(1, 10, null, null));
        model.addAttribute("skills", postService.getAllSkills());
        return "mypage";
    }

}
