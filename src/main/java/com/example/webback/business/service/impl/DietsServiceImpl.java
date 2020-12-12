package com.example.webback.business.service.impl;

import com.example.webback.business.entity.DietsEntity;
import com.example.webback.business.entity.EatingEntity;
import com.example.webback.business.enums.DietsTypeEnum;
import com.example.webback.business.service.DietsService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DietsServiceImpl implements DietsService {

		private EatingEntity initEatingEntity(Integer id, String info, String name){
				EatingEntity eatingEntity = new EatingEntity();
				eatingEntity.setId(id);
				eatingEntity.setInfo(info);
				eatingEntity.setName(name);
				return eatingEntity;
		}

		private DietsEntity init(Integer id, DietsTypeEnum type, String uri, String info, String name, List<EatingEntity> eating){
				DietsEntity dietsEntity = new DietsEntity();
				dietsEntity.setName(name);
				dietsEntity.setInfo(info);
				dietsEntity.setPreviewUri(uri);
				dietsEntity.setType(type);
				dietsEntity.setId(id);
				dietsEntity.setEating(eating);
				return dietsEntity;
		}

		private List<DietsEntity> initList(){
				List<EatingEntity> eatingEntities = new ArrayList<>();
				eatingEntities.add(initEatingEntity(1,"TEST", "NAME"));
				eatingEntities.add(initEatingEntity(2,"TEST", "NAME"));
				eatingEntities.add(initEatingEntity(3,"TEST", "NAME"));

				List<DietsEntity> dietsEntities = new ArrayList<>();
				dietsEntities.add(init(1, DietsTypeEnum.SLIMMING, "uri", "info", "name", eatingEntities));
				dietsEntities.add(init(2, DietsTypeEnum.WEIGHT_GAIN, "uri2", "info2", "name2", eatingEntities));

				return dietsEntities;
		}


		@Override
		public DietsEntity getById(Integer id) {
				return initList().stream().filter(i -> i.getId().equals(id)).findFirst().get();
		}

		@Override
		public List<DietsEntity> getAll() {
				return initList();
		}
}
