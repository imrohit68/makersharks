package com.example.makersharksassessment;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "API DOCUMENTATION FOR MAKERSHARKS ASSESSMENT",
                description = "CONTAINS ALL APIs REQUESTED IN THE ASSIGNMENT",
                version = "v1",
                contact = @Contact(
                        name = " Rohit Singh",
                        email = "sirohit328@gmail.com"
                )
        )
)
@RequiredArgsConstructor
public class MakersharksAssessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakersharksAssessmentApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}
}
