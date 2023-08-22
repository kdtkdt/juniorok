package com.juniorok.juniorok.service;

import com.juniorok.juniorok.domain.Company;
import com.juniorok.juniorok.domain.Post;
import com.juniorok.juniorok.dto.JobType;
import com.juniorok.juniorok.dto.Skill;
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
        long postId = postRepository.findIdByPositionAndCompanyId(postForm.position(), companyId);
        postForm.skills().forEach(skillId -> postRepository.savePositionSkills(postId, Long.parseLong(skillId)));
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

    @Transactional(readOnly = true)
    public List<Post> getPage(int page, int size) {
        return postRepository.findPage((page - 1) * size, size);
    }

    @Transactional(readOnly = true)
    public Post getPostById(long postId) {
        return postRepository.findById(postId);
    }

    
    //어드민 게시글 전부 불러옴.
    public List<Post> getAllPosts() { return postRepository.getAllPosts(); }
    //어드민 게시글 낱개삭제
    public void deletePost(long postId){ postRepository.deletePost(postId); }

    @Transactional(readOnly = true)
    public List<Skill> getAllSkillNames() {
        return postRepository.findAllSkillNames();
    }
}
