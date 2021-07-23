package com.example.adminStudy.service;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.entity.Item;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.ItemApiRequest;
import com.example.adminStudy.model.network.response.ItemApiResponse;
import com.example.adminStudy.repository.ItemRepository;
import com.example.adminStudy.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        // 1. request data
        ItemApiRequest itemApiRequest = request.getData();

        // 2. item 생성
        Item item = Item.builder()
                .status(itemApiRequest.getStatus())
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .brandName(itemApiRequest.getBrandName())
                .registeredAt(LocalDateTime.now())
                .partner(partnerRepository.getOne(itemApiRequest.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);

        // 3. 생성된 데이터 -> UserApiResponse return
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        // id -> repository getOne, getById
        Optional<Item> item = Optional.of(baseRepository.getById(id));

        // item -> itemApiResponse return
        return item.map(m -> response(m)).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        // 1. data
        ItemApiRequest itemApiRequest = request.getData();

        // 2. id -> item 데이터를 찾고
        Item item = baseRepository.getById(itemApiRequest.getId());

        // 3. update
        item.setStatus(itemApiRequest.getStatus())
                .setName(itemApiRequest.getName())
                .setTitle(itemApiRequest.getTitle())
                .setContent(itemApiRequest.getContent())
                .setPrice(itemApiRequest.getPrice())
                .setBrandName(itemApiRequest.getBrandName())
                .setRegisteredAt(LocalDateTime.now())
                .setUnregisteredAt(itemApiRequest.getUnregisteredAt())
                .setPartner(partnerRepository.getOne(itemApiRequest.getPartnerId()));

        Item updateItem = baseRepository.save(item);

        // 4. itemApiResponse
        return response(updateItem);
    }

    @Override
    public Header delete(Long id) {

        // 1. id -> repository -> delete
        Item item = baseRepository.getById(id);
        baseRepository.deleteById(id);

        // 2. return
        if (item != null) {
            return Header.OK();
        }
        else {
            return Header.ERROR("데이터 없음");
        }
    }

    public Header<ItemApiResponse> response (Item item) {

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponse);
    }
}
