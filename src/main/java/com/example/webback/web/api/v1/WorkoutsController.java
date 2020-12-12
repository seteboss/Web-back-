package com.example.webback.web.api.v1;

import static com.example.webback.utils.ApiConstantUtils.ALL;
import static com.example.webback.utils.ApiConstantUtils.WORKOUTS;

import com.example.webback.business.entity.WorkoutsEntity;
import com.example.webback.business.service.WorkoutsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WORKOUTS)
@AllArgsConstructor
public class WorkoutsController {

		private final WorkoutsService workoutsService;


		@GetMapping("/{id}")
		public WorkoutsEntity findById(@PathVariable Integer id){

				return workoutsService.getById(id);
		}

		@GetMapping(ALL)
		public List<WorkoutsEntity> findAll(){
				return workoutsService.getAll();
		}

}
