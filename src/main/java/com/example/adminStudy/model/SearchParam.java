package com.example.adminStudy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchParam {

    private String account;
    private String email;
    private int page;
}
