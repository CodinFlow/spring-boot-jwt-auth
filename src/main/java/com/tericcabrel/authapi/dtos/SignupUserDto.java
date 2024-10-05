package com.tericcabrel.authapi.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupUserDto {

    //@Email(message = "Email should be valid")
    private String email;
    //@Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    private String firstname;
    private String lastname;
    private String address;

}
