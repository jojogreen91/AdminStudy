package com.example.adminStudy.controller;

import com.example.adminStudy.model.SearchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping("/postMethod")
    public SearchParam postMethod (@RequestBody SearchParam searchParam) {

        System.out.println("work!");

        return searchParam;
    }
}
