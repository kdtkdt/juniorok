package com.juniorok.juniorok.domain;

import com.juniorok.juniorok.dto.JobType;
import com.juniorok.juniorok.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private long id;
    private Company company;
    private JobType jobType;
    private String position;
    private LocalDateTime startedAt;
    private LocalDateTime deadline;
    private String location;
    private long minSalary;
    private long maxSalary;
    private String requirements;
    private String preferredRequirements;
    private String postUrl;
    private boolean recommended;
    private boolean deleted;
    private List<Skill> positionSkills;

}
