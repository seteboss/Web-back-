package com.example.webback.web.api.v1;


import static com.example.webback.utils.ApiConstantUtils.ALL;
import static com.example.webback.utils.ApiConstantUtils.DIETS;

import com.example.webback.business.entity.DietsEntity;
import com.example.webback.business.service.DietsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DIETS)
@AllArgsConstructor
public class DietsController {

		private final DietsService dietsService;

		@GetMapping("/{id}")
		public DietsEntity findById(@PathVariable Integer id){
				return dietsService.getById(id);
		}

		@GetMapping(ALL)
		public List<DietsEntity> findAll(){
				return dietsService.getAll();
		}
}
