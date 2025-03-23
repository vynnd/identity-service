package com.dev.identity_service.mapper;

import com.dev.identity_service.dto.request.RoleRequest;
import com.dev.identity_service.dto.response.RoleResponse;
import com.dev.identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
    List<RoleResponse> toListRoleResponse(List<Role> roles);
}
