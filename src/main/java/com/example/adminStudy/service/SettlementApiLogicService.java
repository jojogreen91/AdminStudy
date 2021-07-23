package com.example.adminStudy.service;

import com.example.adminStudy.model.entity.Settlement;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.SettlementApiRequest;
import com.example.adminStudy.model.network.response.SettlementApiResponse;
import com.example.adminStudy.repository.SettlementRepository;
import com.example.adminStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class SettlementApiLogicService extends BaseService<SettlementApiRequest, SettlementApiResponse, Settlement>{

    @Autowired
    private SettlementRepository settlementRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<SettlementApiResponse> create(Header<SettlementApiRequest> request) {
        return null;
    }

    @Override
    public Header<SettlementApiResponse> read(Long userId) {

        // userId를 가지는 Settlement Entity가 존재 한다면
        if (settlementRepository.findByUserId(userId).isPresent()) {
            // 곧바로 read
        }
        // userId를 가지는 Settlement Entity가 존재하지 않는다면
        else {
            // Settlement Entity 생성
            Settlement settlement = Settlement.builder()
                    .userId(userId)
                    .price(BigDecimal.valueOf(userRepository.getById(userId).getOrderGroupList().stream()
                            .map(orderGroup -> orderGroup.getTotalPrice())
                            .map(price -> price.doubleValue())
                            .mapToDouble(price -> Double.valueOf(price))
                            .sum())
                    )
                    .build();

            Settlement newSettlement = settlementRepository.save(settlement);
        }

        // userId를 이용해서 Settlement Entity를 찾고 Header.OK(response(readSettlement)) 반환
        Optional<Settlement> settlement = settlementRepository.findByUserId(userId);
        Settlement readSettlement = settlement.get();

        return Header.OK(response(readSettlement));
    }

    @Override
    public Header<SettlementApiResponse> update(Header<SettlementApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private SettlementApiResponse response (Settlement settlement) {

        SettlementApiResponse settlementApiResponse = SettlementApiResponse.builder()
                .userId(settlement.getUserId())
                .price(settlement.getPrice())
                .build();

        return settlementApiResponse;
    }
}
