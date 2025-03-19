package com.dev.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    String userName;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

}
