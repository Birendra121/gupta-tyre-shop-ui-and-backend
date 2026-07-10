package com.guptatyre.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactMessageDto {

    @NotBlank(message = "Full Name is required")
    @Size(max = 100)
    private String fullName;

    @NotBlank(message = "Mobile Number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$",
            message = "Enter valid mobile number")
    private String mobile;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(min = 10,
            max = 1000,
            message = "Message should be between 10 and 1000 characters")
    private String message;


}