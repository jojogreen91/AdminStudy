package com.example.AdminStudy.service;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.entity.User;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.UserApiRequest;
import com.example.AdminStudy.model.network.response.UserApiResponse;
import com.example.AdminStudy.repository.UserRepository;
import org.hibernate.AssertionFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
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
        Optional<User> user = Optional.of(userRepository.getById(id));

        // user -> userApiResponse return
        return user.map(m -> response(m)).orElseGet(() -> Header.ERROR("데이터 없음"));
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
        return null;
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
