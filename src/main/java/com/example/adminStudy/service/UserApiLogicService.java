package com.example.adminStudy.service;

import com.example.adminStudy.ifs.CrudInterface;
import com.example.adminStudy.model.entity.User;
import com.example.adminStudy.model.enumclass.UserStatus;
import com.example.adminStudy.model.network.Header;
import com.example.adminStudy.model.network.request.UserApiRequest;
import com.example.adminStudy.model.network.response.UserApiResponse;
import com.example.adminStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2차 피드백 과제
        if (userRepository.findByEmail(userApiRequest.getEmail()) != null) {
            return Header.EMAILEXIST();
        }

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 -> UserApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        /// Optional<User> user = Optional.of(userRepository.getById(id));
        User user = userRepository.getById(id);

        // user -> userApiResponse return
        /// return user.map(m -> response(m)).orElseGet(() -> Header.ERROR("데이터 없음"));
        if (user != null) {
            return response(user);
        }
        else  {
            return Header.ERROR("데이터 없음");
        }
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data
        UserApiRequest userApiRequest = request.getData();

        // 2. id -> user 데이터를 찾고
        User user = userRepository.getById(userApiRequest.getId());

        // 3. update
        user.setAccount(userApiRequest.getAccount())
                .setPassword(userApiRequest.getPassword())
                .setStatus(userApiRequest.getStatus())
                .setEmail(userApiRequest.getEmail())
                .setPhoneNumber(userApiRequest.getPhoneNumber())
                .setRegisteredAt(LocalDateTime.now()); // @Accessors(chain = true)을 사용

        User updateUser = userRepository.save(user);

        // 4. userApiResponse
        if (user != null) {
            return response(updateUser);
        }
        else {
            return Header.ERROR("데이터 없음");
        }
    }

    @Override
    public Header delete(Long id) {

        // 1. id -> repository -> delete
        User user = userRepository.getById(id);
        userRepository.deleteById(id);

        // 2. return
        if (user != null) {
            return Header.OK();
        }
        else {
            return Header.ERROR("데이터 없음");
        }
    }

    private Header<UserApiResponse> response (User user) {

        // user -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }
}