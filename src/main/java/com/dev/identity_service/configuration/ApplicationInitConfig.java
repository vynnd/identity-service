package com.dev.identity_service.configuration;

import com.dev.identity_service.entity.Role;
import com.dev.identity_service.entity.User;
import com.dev.identity_service.repository.PermissionRepository;
import com.dev.identity_service.repository.RoleRepository;
import com.dev.identity_service.repository.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "driverClassName", havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserReponsitory userReponsitory, RoleRepository roleRepository,
                                        PermissionRepository permissionRepository){
        return args -> {
            if(roleRepository.findById("ADMIN").isEmpty()){
                Role role = Role.builder()
                        .name("ADMIN")
                        .description("Admin role!")
                        .permissions(new HashSet<>(permissionRepository.findAll()))
                        .build();
                roleRepository.save(role);

                if(userReponsitory.findByUserName("admin").isEmpty()){
                    User user = User.builder()
                            .userName("admin")
                            .password(passwordEncoder.encode("admin"))
                            .roles(Set.of(role))
                            .build();
                    userReponsitory.save(user);
            }
                log.warn("Admin user has been created with default password!");
            }
        };
    }
}
