package com.juniorok.juniorok.form;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public record PostForm (
        String companyName,
        String position,
        int jobType,
        String location,
        long minSalary,
        long maxSalary,
        LocalDateTime startedAt,
        LocalDateTime deadline,
        List<String> skills,
        List<String> benefits,
        int employees,
        int developers,
        URI postUrl,
        URI techBlogUrl,
        String requirements,
        String preferredRequirements
) { }
