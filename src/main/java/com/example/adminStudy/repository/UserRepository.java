package com.example.adminStudy.repository;

import com.example.adminStudy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByPhoneNumberOrderByIdDesc (String phoneNumber);

    // 2차 피드백 과제
    User findByEmail (String email);
}
