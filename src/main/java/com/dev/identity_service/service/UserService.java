package com.dev.identity_service.service;

import com.dev.identity_service.dto.request.UserCreationRequest;
import com.dev.identity_service.dto.request.UserUpdateRequest;
import com.dev.identity_service.dto.response.UserResponse;
import com.dev.identity_service.entity.User;
import com.dev.identity_service.exception.AppException;
import com.dev.identity_service.exception.ErrorCode;
import com.dev.identity_service.mapper.UserMapper;
import com.dev.identity_service.repository.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserReponsitory userReponsitory;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request){

        if(userReponsitory.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){

        User user = userReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userReponsitory.save(user));
    }

    public void deleteUser(String userId){
        userReponsitory.deleteById(userId);
    }

    public List<UserResponse> getUser(){
        return userMapper.toListUserResponse(userReponsitory.findAll());
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userReponsitory.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }
}
