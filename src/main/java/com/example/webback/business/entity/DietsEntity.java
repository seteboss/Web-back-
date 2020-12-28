package com.example.webback.business.entity;

import com.example.webback.business.enums.DietsTypeEnum;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "diets")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DietsEntity extends Model<Integer>{

		@Column(name = "info")
		private String info;

		@Column(name = "type")
		private DietsTypeEnum type;

		@Column(name = "uri")
		private String previewUri;

		@OneToMany(mappedBy = "diets", cascade = CascadeType.ALL)
		private List<EatingEntity> eating;

		@OneToMany(mappedBy = "diets", cascade = CascadeType.ALL)
		private List<UserEntity> user;


}
