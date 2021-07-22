package com.example.adminStudy.service;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.entity.OrderDetail;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.OrderDetailApiRequest;
import com.example.adminStudy.model.network.response.OrderDetailApiResponse;
import com.example.adminStudy.repository.ItemRepository;
import com.example.adminStudy.repository.OrderDetailRepository;
import com.example.adminStudy.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(orderDetailApiRequest.getStatus())
                .arrivalDate(orderDetailApiRequest.getArrivalDate())
                .quantity(orderDetailApiRequest.getQuantity())
                .totalPrice(orderDetailApiRequest.getTotalPrice())
                .item(itemRepository.getById(orderDetailApiRequest.getItemId()))
                .orderGroup(orderGroupRepository.getById(orderDetailApiRequest.getOrderGroupId()))
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {

        OrderDetail orderDetail = orderDetailRepository.getById(id);

        return response(orderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest orderDetailApiRequest = request.getData();

        OrderDetail orderDetail = orderDetailRepository.getById(orderDetailApiRequest.getId())
                .setStatus(orderDetailApiRequest.getStatus())
                .setArrivalDate(orderDetailApiRequest.getArrivalDate())
                .setQuantity(orderDetailApiRequest.getQuantity())
                .setTotalPrice(orderDetailApiRequest.getTotalPrice())
                .setItem(itemRepository.getById(orderDetailApiRequest.getItemId()))
                .setOrderGroup(orderGroupRepository.getById(orderDetailApiRequest.getOrderGroupId()));

        OrderDetail updateOrderDetail = orderDetailRepository.save(orderDetail);

        return response(updateOrderDetail);
    }

    @Override
    public Header delete(Long id) {

        OrderDetail orderDetail = orderDetailRepository.getById(id);

        orderDetailRepository.deleteById(id);

        return Header.OK();
    }

    private Header<OrderDetailApiResponse> response (OrderDetail orderDetail) {

        OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .itemId(orderDetail.getItem().getId())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .build();

        return Header.OK(orderDetailApiResponse);
    }
}
