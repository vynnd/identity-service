package com.dev.identity_service.dto.response;

import com.dev.identity_service.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String id;
    String userName;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    Set<RoleResponse> roles;
}
