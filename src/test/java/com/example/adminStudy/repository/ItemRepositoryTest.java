package com.example.adminStudy.repository;

import com.example.adminStudy.AdminStudyApplicationTests;
import com.example.adminStudy.model.entity.Item;
import com.example.adminStudy.model.enumclass.ItemStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ItemRepositoryTest extends AdminStudyApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void create () {

        Item item = new Item();
        item.setStatus(ItemStatus.REGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다");
        //item.setPrice(BigDecimal.valueOf(90.0000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("AdminServer");
        //item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);

        Assertions.assertNotNull(newItem);
    }
}
