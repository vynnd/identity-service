package com.learn.identity_service.mapper;

import com.learn.identity_service.dto.request.UserCreationRequest;
import com.learn.identity_service.dto.request.UserUpdateRequest;
import com.learn.identity_service.dto.response.UserResponse;
import com.learn.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(source = "firstName", target = "lastName")
//    @Mapping(target = "lastName", ignore = true) -> not mapping column lastName
    User toUser(UserCreationRequest request);

    @Mapping(source = "id", target = "id")
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
