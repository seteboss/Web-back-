package com.example.webback.web.api.dto.read;

import com.example.webback.business.enums.DietsTypeEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DietsReadDto {
    Integer id;
    String info;
    DietsTypeEnum type;
    String previewUri;
    List<EatingReadDto> eating;
}
