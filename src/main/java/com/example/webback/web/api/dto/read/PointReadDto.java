package com.example.webback.web.api.dto.read;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointReadDto {
    LocalDate date;
    Double value;
}
