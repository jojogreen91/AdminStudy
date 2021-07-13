package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.UserApiRequest;
import com.example.AdminStudy.model.network.response.UserApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {


    @Override
    @PostMapping("") // /api/user/
    public Header<UserApiResponse> create(@RequestBody UserApiRequest userApiRequest) {

        return null;
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable Long id) {

        return null;
    }

    @Override
    @PutMapping() // /api/user
    public Header<UserApiResponse> update(@RequestBody UserApiRequest userApiRequest) {

        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable Long id) {

        return null;
    }
}
