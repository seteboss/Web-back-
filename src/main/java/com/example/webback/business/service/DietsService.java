package com.example.webback.business.service;

import com.example.webback.business.entity.DietsEntity;
import java.util.List;

public interface DietsService {
		DietsEntity getById(Integer id);
		List<DietsEntity> getAll();
}
