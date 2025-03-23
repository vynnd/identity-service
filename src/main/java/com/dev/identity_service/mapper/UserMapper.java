package com.dev.identity_service.mapper;

import com.dev.identity_service.dto.request.UserCreationRequest;
import com.dev.identity_service.dto.request.UserUpdateRequest;
import com.dev.identity_service.dto.response.UserResponse;
import com.dev.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    // @Mapping(source = "firstName",target = "lastName")
//     @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);

    List<UserResponse> toListUserResponse(List<User> users);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
