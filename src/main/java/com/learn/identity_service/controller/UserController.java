package com.learn.identity_service.controller;

import com.learn.identity_service.dto.request.ApiResponse;
import com.learn.identity_service.dto.request.UserCreationRequest;
import com.learn.identity_service.dto.request.UserUpdateRequest;
import com.learn.identity_service.dto.response.UserResponse;
import com.learn.identity_service.entity.User;
import com.learn.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId,
                    @RequestBody UserUpdateRequest request ){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("{userId}")
    void deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
    }
}
