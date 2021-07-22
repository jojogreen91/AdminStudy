package com.example.adminStudy.controller;

import com.example.adminStudy.model.SearchParam;
import com.example.adminStudy.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost8080:/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost8080:/api/getMethod
    public String getRequest () {

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost8080:/api/getParameter?id=1234&password=abcd
    public String getParameter (@RequestParam String id, @RequestParam(name = "password") String pwd ) {

        String password = "bbbb";
        System.out.println("id : " + id);
        System.out.println("pwd : " + pwd);

        return id + pwd;
    }

    @GetMapping("/getMultiParameter") // localhost8080:/api/getMultiParameter?account=abcd&email=email@email.com&page=10
    public SearchParam getMultiParameter (SearchParam searchParam) {

        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // {"account" = " ", "email" = " ", "page" = 0}
        return searchParam;
    }

    @GetMapping("/header") // localhost8080:/api/header
    public Header getHeader () {

        // {"resultCode" = "OK", "description" = "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
