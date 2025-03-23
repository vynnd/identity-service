package com.dev.identity_service.service;

import com.dev.identity_service.dto.request.PermissionRequest;
import com.dev.identity_service.dto.response.PermissionResponse;
import com.dev.identity_service.entity.Permission;
import com.dev.identity_service.entity.Role;
import com.dev.identity_service.exception.AppException;
import com.dev.identity_service.exception.ErrorCode;
import com.dev.identity_service.mapper.PermissionMapper;
import com.dev.identity_service.repository.PermissionRepository;
import com.dev.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;
    RoleRepository roleRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){

        Permission permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
        Role role = roleRepository.findById("ADMIN").orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        role.getPermissions().add(permission);
        roleRepository.save(role);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        return permissionMapper.toListPermissionResponse(permissionRepository.findAll());
    }

    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}
