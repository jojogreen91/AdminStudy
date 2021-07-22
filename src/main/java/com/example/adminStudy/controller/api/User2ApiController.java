package com.example.adminStudy.controller.api;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.UserApiRequest;
import com.example.adminStudy.model.network.response.UserApiResponse;
import com.example.adminStudy.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 피드백 2차 과제
@RestController
@RequestMapping("/api/user2")
public class User2ApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {

        return userApiLogicService.create(request);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
