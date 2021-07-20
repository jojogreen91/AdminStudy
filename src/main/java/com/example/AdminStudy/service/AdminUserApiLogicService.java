package com.example.AdminStudy.service;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.entity.AdminUser;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.AdminUserApiRequest;
import com.example.AdminStudy.model.network.response.AdminUserApiResponse;
import com.example.AdminStudy.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest adminUserApiRequest = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(adminUserApiRequest.getAccount())
                .password(adminUserApiRequest.getPassword())
                .status(adminUserApiRequest.getStatus())
                .role(adminUserApiRequest.getRole())
                .lastLoginAt(adminUserApiRequest.getLastLoginAt())
                .passwordUpdatedAt(adminUserApiRequest.getPasswordUpdatedAt())
                .loginFailCount(adminUserApiRequest.getLoginFailCount())
                .registeredAt(LocalDateTime.now())
                .unregisteredAt(adminUserApiRequest.getUnregisteredAt())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {

        AdminUser adminUser = adminUserRepository.getById(id);

        return response(adminUser);
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest adminUserApiRequest = request.getData();

        AdminUser adminUser = adminUserRepository.getById(adminUserApiRequest.getId())
                .setAccount(adminUserApiRequest.getAccount())
                .setPassword(adminUserApiRequest.getPassword())
                .setStatus(adminUserApiRequest.getStatus())
                .setRole(adminUserApiRequest.getRole())
                .setLastLoginAt(adminUserApiRequest.getLastLoginAt())
                .setPasswordUpdatedAt(adminUserApiRequest.getPasswordUpdatedAt())
                .setLoginFailCount(adminUserApiRequest.getLoginFailCount())
                .setRegisteredAt(LocalDateTime.now())
                .setUnregisteredAt(adminUserApiRequest.getUnregisteredAt());

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header delete(Long id) {

        AdminUser adminUser = adminUserRepository.getById(id);

        adminUserRepository.deleteById(id);

        return Header.OK();
    }

    private Header<AdminUserApiResponse> response (AdminUser adminUser) {

        AdminUserApiResponse adminUserApiResponse = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();

        return Header.OK(adminUserApiResponse);
    }
}
