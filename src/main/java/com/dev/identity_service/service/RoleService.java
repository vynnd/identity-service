package com.dev.identity_service.service;

import com.dev.identity_service.dto.request.RoleRequest;
import com.dev.identity_service.dto.response.RoleResponse;
import com.dev.identity_service.entity.Role;
import com.dev.identity_service.mapper.RoleMapper;
import com.dev.identity_service.repository.PermissionRepository;
import com.dev.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public RoleResponse create(RoleRequest request){

        Role role = roleMapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> getAll(){
        return roleMapper.toListRoleResponse(roleRepository.findAll());
    }

    public void deleteRole(String role){
        roleRepository.deleteById(role);
    }
}
