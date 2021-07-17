package com.example.AdminStudy.repository;

import com.example.AdminStudy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByPhoneNumberOrderByIdDesc (String phoneNumber);

    // 2차 피드백 과제
    User findByEmail (String email);
}
