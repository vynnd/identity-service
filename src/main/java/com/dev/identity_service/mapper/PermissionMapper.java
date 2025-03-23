package com.dev.identity_service.mapper;

import com.dev.identity_service.dto.request.PermissionRequest;
import com.dev.identity_service.dto.response.PermissionResponse;
import com.dev.identity_service.entity.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
    List<PermissionResponse> toListPermissionResponse(List<Permission> permissions);
}
