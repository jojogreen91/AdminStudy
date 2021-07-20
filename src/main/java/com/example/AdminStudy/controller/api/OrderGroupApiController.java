package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.OrderGroupApiRequest;
import com.example.AdminStudy.model.network.response.OrderGroupApiResponse;
import com.example.AdminStudy.service.OrderGroupApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("") // /api/orderGroup/
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {

        log.info("{}", request);
        return orderGroupApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/orderGroup/{id}
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {

        log.info("read id : {}", id);
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/orderGroup/
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {

        log.info("{}", request);
        return orderGroupApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/orderGroup/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
