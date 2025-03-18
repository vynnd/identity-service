package com.dev.identity_service.service;

import com.dev.identity_service.dto.request.UserCreationRequest;
import com.dev.identity_service.dto.request.UserUpdateRequest;
import com.dev.identity_service.entity.User;
import com.dev.identity_service.exception.AppException;
import com.dev.identity_service.exception.ErrorCode;
import com.dev.identity_service.repository.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserReponsitory userReponsitory;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userReponsitory.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userReponsitory.save(user);
    }

    public User updateUser(String id, UserUpdateRequest request){
        User user = getUser(id);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userReponsitory.save(user);
    }

    public void deleteUser(String userId){
        userReponsitory.deleteById(userId);
    }

    public List<User> getUser(){
        return userReponsitory.findAll();
    }

    public User getUser(String id){
        return userReponsitory.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
