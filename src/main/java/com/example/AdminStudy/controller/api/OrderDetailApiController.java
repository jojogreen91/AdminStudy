package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.OrderDetailApiRequest;
import com.example.AdminStudy.model.network.response.OrderDetailApiResponse;
import com.example.AdminStudy.service.OrderDetailApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailApiLogicService orderDetailApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderDetailApiResponse> create(@RequestBody Header<OrderDetailApiRequest> request) {

        log.info("{}", request);
        return orderDetailApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderDetailApiResponse> read(@PathVariable Long id) {

        log.info("read id : {}", id);
        return orderDetailApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderDetailApiResponse> update(@RequestBody Header<OrderDetailApiRequest> request) {

        log.info("{}", request);
        return orderDetailApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        log.info("delete id : {}", id);
        return orderDetailApiLogicService.delete(id);
    }
}
