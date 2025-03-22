package com.dev.identity_service.configuration;

import com.dev.identity_service.entity.User;
import com.dev.identity_service.enums.Role;
import com.dev.identity_service.repository.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserReponsitory userReponsitory){
        return args -> {
            if(userReponsitory.findByUserName("admin").isEmpty()){
                User user = User.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(Set.of(Role.USER.name(), Role.ADMIN.name()))
                        .build();
                userReponsitory.save(user);
                log.warn("Admin user has been created with default password!");
            }
        };
    }
}
