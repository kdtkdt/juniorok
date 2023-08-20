package com.juniorok.juniorok.controller;

import com.juniorok.juniorok.form.PostForm;
import com.juniorok.juniorok.service.CompanyService;
import com.juniorok.juniorok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CompanyService companyService;

    @GetMapping("/write")
    public String showPostWriteForm(Model model) {
        model.addAttribute("postForm", PostForm.newInstance());
        model.addAttribute("benefits", postService.getAllBenefitTags());
        return "posts/post_write";
    }

    @PostMapping
    public String savePost(@ModelAttribute PostForm postForm, Model model) {
        model.addAttribute("postForm", postForm);
        long companyId = companyService.saveCompany(postForm);
        postService.savePost(postForm, companyId);
        return "posts/post_detail";
    }
}
