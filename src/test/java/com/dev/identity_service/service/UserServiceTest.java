package com.dev.identity_service.service;

import com.dev.identity_service.dto.request.UserCreationRequest;
import com.dev.identity_service.dto.response.UserResponse;
import com.dev.identity_service.entity.User;
import com.dev.identity_service.exception.AppException;
import com.dev.identity_service.repository.UserReponsitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserReponsitory userReponsitory;

    private UserCreationRequest userCreationRequest;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1998, 10 , 5);
        userCreationRequest = UserCreationRequest.builder()
                .userName("vy_test")
                .firstName("test_01")
                .lastName("test_02")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("9400c9b8bc33")
                .userName("vy_test")
                .firstName("test_01")
                .lastName("test_02")
                .dob(dob)
                .build();

        user = User.builder()
                .id("9400c9b8bc33")
                .userName("vy_test")
                .firstName("test_01")
                .lastName("test_02")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {

        // GIVEN
        Mockito.when(userReponsitory.existsByUserName(Mockito.anyString())).thenReturn(false);
        Mockito.when(userReponsitory.save(Mockito.any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(userCreationRequest);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("9400c9b8bc33");
        Assertions.assertThat(response.getUserName()).isEqualTo("vy_test");
    }

    @Test
    void createUser_userExisted_false() {

        // GIVEN
        Mockito.when(userReponsitory.existsByUserName(Mockito.anyString())).thenReturn(true);

        // THEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(userCreationRequest));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(2001);
        Assertions.assertThat(exception.getMessage()).isEqualTo("User existed!");

    }

    @Test
    @WithMockUser(username = "vy_test")
    void getMyInfor_valid_success(){

        Mockito.when(userReponsitory.findByUserName(Mockito.anyString())).thenReturn(Optional.of(user));
        var response = userService.getMyInfor();
        Assertions.assertThat(response.getUserName()).isEqualTo("vy_test");
        Assertions.assertThat(response.getFirstName()).isEqualTo("test_01");
        Assertions.assertThat(response.getLastName()).isEqualTo("test_02");
    }

    @Test
    @WithMockUser(username = "vy_test")
    void getMyInfor_userNotFound_error(){

        Mockito.when(userReponsitory.findByUserName(Mockito.anyString())).thenReturn(Optional.ofNullable(null));
        var response = assertThrows(AppException.class, () -> userService.getMyInfor());

        Assertions.assertThat(response.getErrorCode().getCode()).isEqualTo(2003);
        Assertions.assertThat(response.getErrorCode().getMessage()).isEqualTo("User not existed!");
    }
}
