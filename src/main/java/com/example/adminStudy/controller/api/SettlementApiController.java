package com.example.adminStudy.controller.api;

import com.example.adminStudy.controller.CrudController;
import com.example.adminStudy.model.entity.Settlement;
import com.example.adminStudy.model.network.request.SettlementApiRequest;
import com.example.adminStudy.model.network.response.SettlementApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settlement")
public class SettlementApiController extends CrudController<SettlementApiRequest, SettlementApiResponse, Settlement> {
}
