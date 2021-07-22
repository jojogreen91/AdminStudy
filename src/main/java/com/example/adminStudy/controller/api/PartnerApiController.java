package com.example.adminStudy.controller.api;

import com.example.adminStudy.controller.CrudController;
import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.entity.Partner;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.PartnerApiRequest;
import com.example.adminStudy.model.network.response.PartnerApiResponse;
import com.example.adminStudy.service.PartnerApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
