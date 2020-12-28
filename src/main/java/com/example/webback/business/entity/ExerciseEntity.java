package com.example.webback.business.entity;


import com.example.webback.business.enums.ExerciseTypeEnum;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "exercise")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExerciseEntity extends Model<Integer> {

		@Column(name = "info")
		private String info;

		@Column(name = "type")
		private ExerciseTypeEnum type;

		@OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
		private List<ApproachEntity> approach;

		@ManyToOne
		@NotNull
		@JoinColumn(name = "workouts_id", referencedColumnName = "id")
		private WorkoutsEntity workouts;

}
