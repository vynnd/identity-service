package com.dev.identity_service.service;

import com.dev.identity_service.dto.request.UserCreationRequest;
import com.dev.identity_service.dto.request.UserUpdateRequest;
import com.dev.identity_service.dto.response.UserResponse;
import com.dev.identity_service.entity.User;
import com.dev.identity_service.enums.Role;
import com.dev.identity_service.exception.AppException;
import com.dev.identity_service.exception.ErrorCode;
import com.dev.identity_service.mapper.UserMapper;
import com.dev.identity_service.repository.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserReponsitory userReponsitory;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){

        if(userReponsitory.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.USER.name()));

        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public UserResponse getMyInfor(){
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        User user = userReponsitory.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){

        User user = userReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public void deleteUser(String userId){
        userReponsitory.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser(){
        return userMapper.toListUserResponse(userReponsitory.findAll());
    }

    @PostAuthorize("returnObject.userName == authentication.name or hasRole('ADMIN')")
    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }
}
