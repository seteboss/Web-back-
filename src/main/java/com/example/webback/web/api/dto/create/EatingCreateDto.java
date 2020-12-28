package com.example.webback.web.api.dto.create;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EatingCreateDto {
    String info;
}
