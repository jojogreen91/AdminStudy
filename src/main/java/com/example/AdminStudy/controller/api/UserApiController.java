package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.UserApiRequest;
import com.example.AdminStudy.model.network.response.UserApiResponse;
import com.example.AdminStudy.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // /api/user/
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {

        log.info("{}", request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable Long id) {

        return null;
    }

    @Override
    @PutMapping() // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {

        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable Long id) {

        return null;
    }
}
