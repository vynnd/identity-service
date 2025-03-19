package com.dev.identity_service.controller;

import com.dev.identity_service.dto.request.UserCreationRequest;
import com.dev.identity_service.dto.request.UserUpdateRequest;
import com.dev.identity_service.dto.response.ApiResponse;
import com.dev.identity_service.dto.response.UserResponse;
import com.dev.identity_service.entity.User;
import com.dev.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
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
    List<UserResponse> getUsers(){
        return userService.getUser();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
                    @PathVariable("userId") String userId){
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted!";
    }
}
