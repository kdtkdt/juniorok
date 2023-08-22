package com.juniorok.juniorok.domain;

import com.juniorok.juniorok.dto.Address;
import com.juniorok.juniorok.dto.Benefit;
import com.juniorok.juniorok.dto.JoinLeave;
import com.juniorok.juniorok.dto.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    private long id;
    private String name;
    private String ceo;
    private String introduction;
    private String homepage;
    private String techBlog;
    private int employees;
    private int developers;
    private long revenue;
    private long avgSalary;
    private long businessNumber;
    private JoinLeave joinLeave;
    private List<Address> addresses;
    private List<Skill> skills;
    private List<Benefit> benefits;

}
