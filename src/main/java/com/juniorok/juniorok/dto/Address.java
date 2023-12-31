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
public class Address {
    long id;
    Company company;
    String state;
    String city;
    String others;
}
