package com.juniorok.juniorok.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reports {
    long id;
    long postid;
    long companyid;
    long userid;
    String reason;
    boolean cleared;
    String companyname;
}
