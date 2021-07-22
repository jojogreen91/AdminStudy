package com.example.adminStudy.controller.api;

import com.example.adminStudy.controller.CrudController;
import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.ItemApiRequest;
import com.example.adminStudy.model.network.response.ItemApiResponse;
import com.example.adminStudy.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @PostConstruct // static method 처럼 미리 실행되어 있는 설정의 메서드
    public void init() {
        //this.baseService = itemApiLogicService; // 이거도 가능
        baseService = itemApiLogicService;
    }
}
