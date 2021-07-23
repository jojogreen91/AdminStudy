package com.example.adminStudy.controller.api;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.UserApiRequest;
import com.example.adminStudy.model.network.response.UserApiResponse;
import com.example.adminStudy.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {

        log.info("read id : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping() // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {

        log.info("{}", request);
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable Long id) {

        log.info("delete id : {}", id);
        return userApiLogicService.delete(id);
    }

    @GetMapping("")
    public Header<List<UserApiResponse>> search (@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {

        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }
}
