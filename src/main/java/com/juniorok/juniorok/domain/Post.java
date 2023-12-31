package com.juniorok.juniorok.domain;

import com.juniorok.juniorok.dto.JobType;
import com.juniorok.juniorok.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private long id;
    private String position;
    private LocalDate startedAt;
    private LocalDate deadline;
    private String location;
    private long minSalary;
    private long maxSalary;
    private String requirements;
    private String preferredRequirements;
    private String postUrl;
    private boolean recommended;
    private boolean deleted;
    private Company company;
    private JobType jobType;
    private List<Skill> positionSkills;

}
