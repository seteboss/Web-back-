package com.example.webback.web.api.dto.read;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutReadSmallDto {
    Long id;
    String name;
    String info;
    String previewUri;
}
