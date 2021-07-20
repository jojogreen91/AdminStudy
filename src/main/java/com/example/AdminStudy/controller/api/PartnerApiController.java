package com.example.AdminStudy.controller.api;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.PartnerApiRequest;
import com.example.AdminStudy.model.network.response.PartnerApiResponse;
import com.example.AdminStudy.service.PartnerApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {

        log.info("{}", request);
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable Long id) {

        log.info("read id : {}", id);
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {

        log.info("{}", request);
        return partnerApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {

        log.info("delete id : {}", id);
        return partnerApiLogicService.delete(id);
    }
}
