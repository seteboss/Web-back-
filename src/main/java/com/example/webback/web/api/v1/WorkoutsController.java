package com.example.webback.web.api.v1;

import static com.example.webback.utils.ApiConstantUtils.ALL;
import static com.example.webback.utils.ApiConstantUtils.WORKOUTS;

import com.example.webback.business.entity.WorkoutsEntity;
import com.example.webback.business.service.WorkoutsService;
import java.util.List;

import com.example.webback.web.api.dto.create.WorkoutCreateDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadSmallDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WORKOUTS)
@AllArgsConstructor
public class WorkoutsController {

		private final WorkoutsService workoutsService;

		@GetMapping("/{id}")
		public WorkoutReadDto findById(@PathVariable Integer id){
				return workoutsService.findDtoById(id);
		}

		@GetMapping(ALL)
		public List<WorkoutReadSmallDto> findAll(){
				return workoutsService.findAll();
		}

		@PostMapping("/save_workout")
		public void save(@RequestBody WorkoutCreateDto dto){
			workoutsService.saveDto(dto);
		}

}
