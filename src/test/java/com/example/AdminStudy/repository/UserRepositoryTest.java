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

        String account = "Test02";
        String password = "Test02";
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

        User userBuilderTest = User.builder()
                .account("조한석")
                .password("password")
                .email("iawbg13@naver.com")
                .build(); // Lombok의 @Builder를 활용한 인스턴스 생성

        User userChainTest = new User().setAccount("박지연").setStatus("등록됨"); // Lombok의 Chain을 활용한 인스턴스 생성
        userChainTest.setStatus("해제됨").setPhoneNumber("010-0000-0000"); // Lombok의 Chain을 활용한 인스턴스 수정

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
                System.out.println("주문 상품 : " + od.getItem().getName());
                System.out.println("고객센터 번호 : " + od.getItem().getPartner().getCallCenter());
                System.out.println("파트너사 이름 : " + od.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + od.getItem().getPartner().getCategory().getTitle());
            });
        });

        Assertions.assertNotNull(user);
    }
}
