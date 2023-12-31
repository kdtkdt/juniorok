package com.juniorok.juniorok.form;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public record PostForm (
        String companyName,
        long businessNumber,
        String position,
        int jobType,
        String location,
        long minSalary,
        long maxSalary,
        LocalDate startedAt,
        LocalDate deadline,
        List<String> skills,
        List<String> benefits,
        int employees,
        int developers,
        URI postUrl,
        URI techBlogUrl,
        URI homepageUrl,
        String requirements,
        String preferredRequirements,
        MultipartFile logoFile
) {
    public static PostForm newInstance() {
        return new PostForm(
                "",
                0,
                "",
                0,
                "",
                0,
                0,
                LocalDate.now(),
                LocalDate.now(),
                Collections.emptyList(),
                Collections.emptyList(),
                0,
                -1,
                URI.create(""),
                URI.create(""),
                URI.create(""),
                "",
                "",
                null);
    }
}
