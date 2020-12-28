package com.example.webback.web.api.dto.update;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private UUID id;
    private String firstName;
    private String secondName;
    private String photoUri;
}
