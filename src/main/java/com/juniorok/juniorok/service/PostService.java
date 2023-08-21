package com.juniorok.juniorok.service;

import com.juniorok.juniorok.domain.Company;
import com.juniorok.juniorok.domain.Post;
import com.juniorok.juniorok.dto.JobType;
import com.juniorok.juniorok.form.PostForm;
import com.juniorok.juniorok.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostForm postForm, long companyId) {
        postRepository.save(extractPostInfo(postForm, companyId));
    }

    private Post extractPostInfo(PostForm postForm, long companyId) {
        return Post.builder()
                .company(Company.builder().id(companyId).build())
                .jobType(JobType.builder().id(postForm.jobType()).build())
                .postUrl(postForm.postUrl().toString())
                .position(postForm.position())
                .startedAt(postForm.startedAt())
                .deadline(postForm.deadline())
                .location(postForm.location())
                .minSalary(postForm.minSalary())
                .maxSalary(postForm.maxSalary())
                .requirements(postForm.requirements())
                .preferredRequirements(postForm.preferredRequirements())
                .build();
    }

}
