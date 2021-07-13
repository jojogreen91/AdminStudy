package com.example.AdminStudy.repository;

import com.example.AdminStudy.AdminStudyApplicationTests;
import com.example.AdminStudy.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminStudyApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void create () {

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(newUser.getAccount(), account);
    }

    @Test
    @Transactional
    public void read () {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.getOrderGroupList().stream().forEach(og -> {
            System.out.println("------주문 묶음------");
            System.out.println("이름 : " + og.getRevName());
            System.out.println("총금액 : " + og.getTotalPrice());
            System.out.println("주소 : " + og.getRevAddress());

            System.out.println("------주문 상세------");
            og.getOrderDetailList().stream().forEach(od -> {
                System.out.println("상태 : " + od.getStatus());
                System.out.println("상품 개수 : " + od.getQuantity());
            });
        });

        Assertions.assertNotNull(user);
    }
}
