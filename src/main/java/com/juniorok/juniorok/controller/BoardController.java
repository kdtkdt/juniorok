package com.juniorok.juniorok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"/main", "/"})
    public String showMainBoard() {
        return "mainboard";
    }

    @GetMapping("/mypage")
    public String showMyPage() {
        return "mypage";
    }

}
