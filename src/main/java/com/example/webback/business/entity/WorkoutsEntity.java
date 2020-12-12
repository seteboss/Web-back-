package com.example.webback.business.entity;


import com.example.webback.business.enums.ComplexityEnum;
import com.example.webback.business.enums.WorkoutsTypeEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutsEntity extends Model<Integer> {

		private String info;

		private WorkoutsTypeEnum type;

		private ComplexityEnum complexity;

		private String previewUri;

		private List<ExerciseEntity> exercise;

}
