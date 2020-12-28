package com.example.webback.web.api.dto.create;


import com.example.webback.business.enums.ComplexityEnum;
import com.example.webback.business.enums.WorkoutsTypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutCreateDto {
    Long id;
    String info;
    WorkoutsTypeEnum type;
    ComplexityEnum complexity;
    String previewUri;
    List<ExerciseCreateDto> exercise;
}
