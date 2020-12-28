package com.example.webback.business.entity;


import com.example.webback.business.enums.ComplexityEnum;
import com.example.webback.business.enums.WorkoutsTypeEnum;
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
@Table(name = "workouts")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WorkoutsEntity extends Model<Integer> {

		@Column(name = "info")
		private String info;

		@Column(name = "type")
		private WorkoutsTypeEnum type;

		@Column(name = "complexity")
		private ComplexityEnum complexity;

		@Column(name = "uri")
		private String previewUri;

		@OneToMany(mappedBy = "workouts", cascade = CascadeType.ALL)
		private List<ExerciseEntity> exercise;

		@OneToMany(mappedBy = "workouts", cascade = CascadeType.ALL)
		private List<UserEntity> user;
}
