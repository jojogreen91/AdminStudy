package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.ItemApiRequest;
import com.example.AdminStudy.model.network.response.ItemApiResponse;
import com.example.AdminStudy.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("") // /api/item/
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {

        log.info("{}", request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // api/item/{id}
    public Header<ItemApiResponse> read(@PathVariable Long id) {

        log.info("read id : {}", id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // api/item/
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // api/item/{id}
    public Header delete(@PathVariable Long id) {
        return null;
    }
}
