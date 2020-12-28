package com.example.webback.web.api.dto.read;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EatingReadDto {
    Integer id;
    String info;
}
