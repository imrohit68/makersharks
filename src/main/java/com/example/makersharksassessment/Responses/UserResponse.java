package com.example.makersharksassessment.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(
        description = "User Details Response for User Fetch Endpoint"
)
public class UserResponse {
    @Schema(
            description = "Username of the user",
            example = "imrohit68"
    )
    private String username;
    @Schema(
            description = "Email of the user",
            example = "sirohit328@gmail.com"
    )
    private String email;
    @Schema(
            description = "Status of request",
            example = "true"
    )
    private boolean status;
}
