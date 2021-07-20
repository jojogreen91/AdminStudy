package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.CategoryApiRequest;
import com.example.AdminStudy.model.network.response.CategoryApiResponse;
import com.example.AdminStudy.service.CategoryApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryApiController implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @Override
    @PostMapping("")
    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {

        log.info("{}", request);
        return categoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryApiResponse> read(@PathVariable Long id) {

        log.info("read id : {}", id);
        return categoryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {

        log.info("{}", request);
        return categoryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        log.info("delete id : {}", id);
        return categoryApiLogicService.delete(id);
    }
}
