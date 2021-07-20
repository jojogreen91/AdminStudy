package com.example.AdminStudy.service;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.entity.Partner;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.PartnerApiRequest;
import com.example.AdminStudy.model.network.response.PartnerApiResponse;
import com.example.AdminStudy.repository.CategoryRepository;
import com.example.AdminStudy.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        PartnerApiRequest partnerApiRequest = request.getData();

        Partner partner = Partner.builder()
                .name(partnerApiRequest.getName())
                .status(partnerApiRequest.getStatus())
                .address(partnerApiRequest.getAddress())
                .callCenter(partnerApiRequest.getCallCenter())
                .partnerNumber(partnerApiRequest.getPartnerNumber())
                .businessNumber(partnerApiRequest.getBusinessNumber())
                .ceoName(partnerApiRequest.getCeoName())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(partnerApiRequest.getUnregisteredAt())
                .category(categoryRepository.getById(partnerApiRequest.getCategoryId()))
                .build();

        Partner newPartner = partnerRepository.save(partner);

        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {

        Partner partner = partnerRepository.getById(id);

        return response(partner);
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        PartnerApiRequest partnerApiRequest = request.getData();

        Partner partner = partnerRepository.getById(partnerApiRequest.getId())
                .setName(partnerApiRequest.getName())
                .setStatus(partnerApiRequest.getStatus())
                .setAddress(partnerApiRequest.getAddress())
                .setCallCenter(partnerApiRequest.getCallCenter())
                .setPartnerNumber(partnerApiRequest.getPartnerNumber())
                .setBusinessNumber(partnerApiRequest.getBusinessNumber())
                .setCeoName(partnerApiRequest.getCeoName())
                .setRegisteredAt(LocalDateTime.now())
                .setUnregisteredAt(partnerApiRequest.getUnregisteredAt())
                .setCategory(categoryRepository.getById(partnerApiRequest.getCategoryId()));

        Partner updatePartner = partnerRepository.save(partner);

        return response(updatePartner);
    }

    @Override
    public Header delete(Long id) {

        Partner partner = partnerRepository.getById(id);

        partnerRepository.deleteById(id);

        return Header.OK();
    }

    private Header<PartnerApiResponse> response (Partner partner) {

        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.OK(partnerApiResponse);
    }
}
