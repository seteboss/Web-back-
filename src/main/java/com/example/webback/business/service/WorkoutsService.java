package com.example.webback.business.service;

import com.example.webback.business.entity.WorkoutsEntity;
import java.util.List;

public interface WorkoutsService {
		WorkoutsEntity getById(Integer id);
		List<WorkoutsEntity> getAll();
}
