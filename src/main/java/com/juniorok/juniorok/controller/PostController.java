package com.juniorok.juniorok.controller;


import com.juniorok.juniorok.config.ApiConfig;
import com.juniorok.juniorok.domain.Post;
import com.juniorok.juniorok.form.PostForm;
import com.juniorok.juniorok.service.CompanyService;
import com.juniorok.juniorok.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final CompanyService companyService;
    private final ApiConfig apiConfig;


    @GetMapping("/write")
    public String showPostWriteForm(Model model) {
        model.addAttribute("postForm", PostForm.newInstance());
        model.addAttribute("benefits", companyService.getAllBenefitTags());
        model.addAttribute("skills", postService.getAllSkills());
        return "posts/post_write";
    }

    @PostMapping
    public String savePost(@ModelAttribute PostForm postForm, Model model) {
        long companyId = companyService.saveCompany(postForm);
        postService.savePost(postForm, companyId);
        return "mainboard";
    }

    @GetMapping("/{id}")
    public String showPostDetail(@PathVariable long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        //조회(현재는 근무지)
        model.addAttribute("location", post.getLocation());
        model.addAttribute("kakaoMapAppkey", apiConfig.getKakaoMapAppkey());
        return "posts/post_modal::post_details_modal";
    }

    @GetMapping("/list")
    public String showPostList(Model model,
                                        @RequestParam(name = "skills") String skills,
                                        @RequestParam(name = "keyword") String keyword,
                                        @RequestParam(name = "page", defaultValue = "1") int page) {
        var posts = postService.getPage(page, 6, keyword,
                skills.isEmpty() ? Collections.emptyList() : List.of(skills.split(",")));
        model.addAttribute("posts", posts);
        return "posts/post_list_card::post_list_card";
    }


    //수정페이지
    @GetMapping("/modify/{postId}")
    public String showPostModifyForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("posts",post);
        model.addAttribute("benefits", companyService.getAllBenefitTags());
        return "posts/post_modify";
    }

    @GetMapping("/report")
    public String report() {
        return "errorpage";
    }

    //추천공고낱개등록
    @GetMapping("/recommendPost/{postId}")
    public ResponseEntity<String> recommendPost(@PathVariable("postId") Long postId) {
        System.out.println(postId);

        try {
            postService.recommendPost(postId);
            return ResponseEntity.ok("Post recommended successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while recommending the post");
        }
    }

         //추천공고낱개등록

        //권한삭제
        @GetMapping("/unrecommended")
        public ResponseEntity<String> unrecommended(@RequestParam("ids[]") List<Long> ids) {
//           System.out.println(ids);
            postService.unrecommended(ids);
            return ResponseEntity.ok("authority successful");
        }



}
