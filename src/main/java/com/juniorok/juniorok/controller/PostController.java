package com.juniorok.juniorok.controller;

import com.juniorok.juniorok.form.PostForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @GetMapping("/write")
    public String showPostWriteForm() {
        return "post_write";
    }

    @PostMapping
    public String savePost(@ModelAttribute PostForm postForm, Model model) {
        model.addAttribute("postForm", postForm);
        return "post_detail";
    }
}
