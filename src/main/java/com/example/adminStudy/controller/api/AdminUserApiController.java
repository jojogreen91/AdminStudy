package com.example.adminStudy.controller.api;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.AdminUserApiRequest;
import com.example.adminStudy.model.network.response.AdminUserApiResponse;
import com.example.adminStudy.service.AdminUserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserApiLogicService adminUserApiLogicService;

    @Override
    @PostMapping("")
    public Header<AdminUserApiResponse> create(@RequestBody Header<AdminUserApiRequest> request) {

        log.info("{}", request);
        return adminUserApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminUserApiResponse> read(@PathVariable Long id) {

        log.info("read id : {}", id);
        return adminUserApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<AdminUserApiResponse> update(@RequestBody Header<AdminUserApiRequest> request) {

        log.info("{}", request);
        return adminUserApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        log.info("delete id : {}", id);
        return adminUserApiLogicService.delete(id);
    }
}
