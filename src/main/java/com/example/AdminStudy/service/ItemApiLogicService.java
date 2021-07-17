package com.example.AdminStudy.service;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.entity.Item;
import com.example.AdminStudy.model.entity.User;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.ItemApiRequest;
import com.example.AdminStudy.model.network.request.UserApiRequest;
import com.example.AdminStudy.model.network.response.ItemApiResponse;
import com.example.AdminStudy.repository.ItemRepository;
import com.example.AdminStudy.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;
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

        Item newItem = itemRepository.save(item);

        // 3. 생성된 데이터 -> UserApiResponse return
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        // id -> repository getOne, getById
        Optional<Item> item = Optional.of(itemRepository.getById(id));

        // item -> itemApiResponse return
        return item.map(m -> response(m)).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        // 1. data
        ItemApiRequest itemApiRequest = request.getData();

        // 2. id -> item 데이터를 찾고
        Item item = itemRepository.getById(itemApiRequest.getId());

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

        Item updateItem = itemRepository.save(item);

        // 4. itemApiResponse
        return response(updateItem);
    }

    @Override
    public Header delete(Long id) {

        // 1. id -> repository -> delete
        Item item = itemRepository.getById(id);
        itemRepository.deleteById(id);

        // 2. return
        if (item != null) {
            return Header.OK();
        }
        else {
            return Header.ERROR("데이터 없음");
        }
    }

    private Header<ItemApiResponse> response (Item item) {

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
