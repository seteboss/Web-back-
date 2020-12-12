package com.example.webback.business.entity;

import com.example.webback.business.enums.DietsTypeEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietsEntity extends Model<Integer>{

		private String info;
		private DietsTypeEnum type;
		private String previewUri;
		private List<EatingEntity> eating;

}
