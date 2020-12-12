package com.example.webback.business.service.impl;

import com.example.webback.business.entity.UserEntity;
import com.example.webback.business.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


		private List<UserEntity> init(){
				List<UserEntity> userEntities = new ArrayList<>();
				userEntities.add(new UserEntity(1, "Андреев", "Олег", "uri1", 170, 70.0  ));
				userEntities.add(new UserEntity(2, "Russell", "George", "uri2", 190, 80.0  ));
				userEntities.add(new UserEntity(3, "Немыкина", "Алина", "uri2", 165, 50.0  ));
				return userEntities;
		}

		@Override
		public UserEntity getById(Integer id) {
				return init().stream().filter(i -> i.getId().equals(id)).findFirst().get();
		}
}
