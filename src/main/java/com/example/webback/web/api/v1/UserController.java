package com.example.webback.web.api.v1;

import static com.example.webback.utils.ApiConstantUtils.USER;

import com.example.webback.business.entity.UserEntity;
import com.example.webback.business.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(USER)
@AllArgsConstructor
public class UserController {

		private final UserService userService;

		@GetMapping("/{id}")
		public UserEntity findById(@PathVariable Integer id){

				return userService.getById(id);
		}
}
