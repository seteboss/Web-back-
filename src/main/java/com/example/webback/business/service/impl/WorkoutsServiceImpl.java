package com.example.webback.business.service.impl;

import com.example.webback.business.entity.ApproachEntity;
import com.example.webback.business.entity.ExerciseEntity;
import com.example.webback.business.entity.WorkoutsEntity;
import com.example.webback.business.enums.ComplexityEnum;
import com.example.webback.business.enums.ExerciseTypeEnum;
import com.example.webback.business.enums.WorkoutsTypeEnum;
import com.example.webback.business.service.WorkoutsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WorkoutsServiceImpl implements WorkoutsService {

		private List<ApproachEntity> initApproachEntitys(){
				ApproachEntity approachEntity = new ApproachEntity();
				approachEntity.setId(1);
				approachEntity.setReiterationCount(10);
				List<ApproachEntity> approachEntities = new ArrayList<>();
				approachEntities.add(approachEntity);
				approachEntities.add(approachEntity);
				approachEntities.add(approachEntity);
				return approachEntities;
		}

		private List<ExerciseEntity> initExerciseEntity(){
				ExerciseEntity exerciseEntity = new ExerciseEntity();
				exerciseEntity.setId(1);
				exerciseEntity.setName("test");
				exerciseEntity.setInfo("info");
				exerciseEntity.setType(ExerciseTypeEnum.STATIC);
				exerciseEntity.setApproach(initApproachEntitys());

				List<ExerciseEntity> exerciseEntities = new ArrayList<>();
				exerciseEntities.add(exerciseEntity);
				exerciseEntities.add(exerciseEntity);
				exerciseEntities.add(exerciseEntity);
				return exerciseEntities;
		}

		private List<WorkoutsEntity> init(){
				WorkoutsEntity workoutsEntity = new WorkoutsEntity();
				workoutsEntity.setId(1);
				workoutsEntity.setName("test");
				workoutsEntity.setComplexity(ComplexityEnum.AVERAGE);
				workoutsEntity.setInfo("info");
				workoutsEntity.setPreviewUri("uri");
				workoutsEntity.setExercise(initExerciseEntity());
				workoutsEntity.setType(WorkoutsTypeEnum.WEIGHT_GAIN);

				WorkoutsEntity workoutsEntity2 = new WorkoutsEntity();
				workoutsEntity.setId(2);
				workoutsEntity.setName("test2");
				workoutsEntity.setComplexity(ComplexityEnum.COMPLICATED);
				workoutsEntity.setInfo("info");
				workoutsEntity.setPreviewUri("uri");
				workoutsEntity.setExercise(initExerciseEntity());
				workoutsEntity.setType(WorkoutsTypeEnum.WEIGHT_GAIN);

				List<WorkoutsEntity> workoutsEntities = new ArrayList<>();
				workoutsEntities.add(workoutsEntity);
				workoutsEntities.add(workoutsEntity2);
				return  workoutsEntities;


		}


		@Override public WorkoutsEntity getById(Integer id) {
				return init().stream().filter(i -> i.getId().equals(id)).findFirst().get();
		}

		@Override public List<WorkoutsEntity> getAll() {
				return init();
		}
}
