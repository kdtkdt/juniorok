package com.juniorok.juniorok.dto;

import com.juniorok.juniorok.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinLeave {
    private Company company;
    private int year;
    private int month;
    private int join;
    private int leave;
}
