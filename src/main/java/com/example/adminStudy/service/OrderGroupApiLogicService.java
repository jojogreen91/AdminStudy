package com.example.adminStudy.service;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.entity.OrderGroup;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.OrderGroupApiRequest;
import com.example.adminStudy.model.network.response.OrderGroupApiResponse;
import com.example.adminStudy.repository.OrderGroupRepository;
import com.example.adminStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(orderGroupApiRequest.getStatus())
                .orderType(orderGroupApiRequest.getOrderType())
                .revAddress(orderGroupApiRequest.getRevAddress())
                .revName(orderGroupApiRequest.getRevName())
                .paymentType(orderGroupApiRequest.getPaymentType())
                .totalPrice(orderGroupApiRequest.getTotalPrice())
                .totalQuantity(orderGroupApiRequest.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .arrivalDate(orderGroupApiRequest.getArrivalDate())
                .user(userRepository.getById(orderGroupApiRequest.getUserId()))
                .build();

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {

        OrderGroup orderGroup = orderGroupRepository.getById(id);

        return response(orderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest orderGroupApiRequest = request.getData();

        OrderGroup orderGroup = orderGroupRepository.getById(orderGroupApiRequest.getId());

        orderGroup.setStatus(orderGroupApiRequest.getStatus())
                .setOrderType(orderGroupApiRequest.getOrderType())
                .setRevAddress(orderGroupApiRequest.getRevAddress())
                .setRevName(orderGroupApiRequest.getRevName())
                .setPaymentType(orderGroupApiRequest.getPaymentType())
                .setTotalPrice(orderGroupApiRequest.getTotalPrice())
                .setTotalQuantity(orderGroupApiRequest.getTotalQuantity())
                .setOrderAt(orderGroupApiRequest.getOrderAt())
                .setArrivalDate(orderGroupApiRequest.getArrivalDate())
                .setUser(userRepository.getById(orderGroupApiRequest.getUserId()));

        OrderGroup updateOrderGroup = orderGroupRepository.save(orderGroup);

        return response(updateOrderGroup);
    }

    @Override
    public Header delete(Long id) {

        OrderGroup orderGroup = orderGroupRepository.getById(id);

        orderGroupRepository.deleteById(id);

        if (orderGroup != null) {
            return Header.OK();
        }
        else {
            return Header.ERROR("데이터 없음");
        }
    }

    private Header<OrderGroupApiResponse> response (OrderGroup orderGroup) {

        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(orderGroupApiResponse);
    }
}
