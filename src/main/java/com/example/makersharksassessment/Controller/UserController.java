package com.example.makersharksassessment.Controller;

import com.example.makersharksassessment.DTO.UsersDTO;
import com.example.makersharksassessment.Responses.ErrorResponse;
import com.example.makersharksassessment.Responses.SuccessResponse;
import com.example.makersharksassessment.Responses.UserResponse;
import com.example.makersharksassessment.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    @Operation(
            summary = "API Endpoint To Add User",
            description = "Takes User Details As Body To Add User To Database"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created",
                    content = @Content(
                            schema = @Schema(implementation = SuccessResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "HTTP BAD_REQUEST Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public ResponseEntity<SuccessResponse> addUser(@RequestBody @Valid UsersDTO usersDTO){
        service.addUser(usersDTO);
        return new ResponseEntity<>(new SuccessResponse(
                "User was successfully added",true
        ), HttpStatus.CREATED);
    }
    @Operation(
            summary = "API Endpoint Get User Details",
            description = "Takes Username As Parameter To Fetch User Detail From Database"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = UserResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "HTTP BAD_REQUEST Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @GetMapping("/fetch")
    /* TODO : Security Measures
    We need to secure this endpoint using spring security and
    implement login mechanism (example :- JWT Token based Authentication),
    so the data is only returned to verified users.
    Additionally,we can implement authorization so that user only with specific roles can access this endpoint(ex :- admin)
    */
    public ResponseEntity<UserResponse> getUser(@RequestParam String username) {
        UsersDTO user = service.getUser(username);
        return new ResponseEntity<>(new UserResponse(user.getUsername(),user.getEmail(),true),HttpStatus.OK);
    }
}
