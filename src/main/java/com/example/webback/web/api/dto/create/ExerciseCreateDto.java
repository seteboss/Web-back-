package com.example.webback.web.api.dto.create;

import com.example.webback.business.enums.ExerciseTypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExerciseCreateDto {
    String info;
    ExerciseTypeEnum type;
    List<ApproachrCreateDto> approach;
}
