package com.example.webback.web.api.dto.create;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    private String username; //email
    private String password;
    private String firstName;
    private String secondName;
}
