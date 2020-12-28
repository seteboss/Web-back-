package com.example.webback.web.api.dto.create;

import com.example.webback.business.enums.DietsTypeEnum;
import com.example.webback.web.api.dto.read.EatingReadDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DietsCreateDto {
    String info;
    DietsTypeEnum type;
    String previewUri;
    List<EatingCreateDto> eating;
}
