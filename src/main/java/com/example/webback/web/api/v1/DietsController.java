package com.example.webback.web.api.v1;


import static com.example.webback.utils.ApiConstantUtils.ALL;
import static com.example.webback.utils.ApiConstantUtils.DIETS;

import com.example.webback.business.entity.DietsEntity;
import com.example.webback.business.service.DietsService;
import java.util.List;

import com.example.webback.web.api.dto.create.DietsCreateDto;
import com.example.webback.web.api.dto.read.DietsReadDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DIETS)
@AllArgsConstructor
public class DietsController {

		private final DietsService dietsService;

		@GetMapping("/{id}")
		public DietsReadDto findById(@PathVariable Integer id){
				return dietsService.findDtoById(id);
		}

		@GetMapping(ALL)
		public List<DietsReadDto> findAll(){
				return dietsService.findAll();
		}

		@PostMapping("/save_diet")
		public void save(@RequestBody DietsCreateDto dietsCreateDto){
			dietsService.saveDto(dietsCreateDto);
		}

}
