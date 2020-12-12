package com.example.webback.business.entity;


import com.example.webback.business.enums.ExerciseTypeEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseEntity extends Model<Integer> {

		private String info;

		private ExerciseTypeEnum type;

		private List<ApproachEntity> approach;

}
