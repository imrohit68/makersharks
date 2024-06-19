package com.example.makersharksassessment.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3,message = "Username must be of 3 or more characters")
    @Schema(
            description = "Username of the User",
            example = "imrohit68"
    )
    private String username;
    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Schema(
            description = "Email of the User",
            example = "sirohit328@gmail.com"
    )
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Schema(
            description = "Password of the User",
            example = "xyz"
    )
    private String password;
}
