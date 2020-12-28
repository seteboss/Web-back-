package com.example.webback.business.entity;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "approach")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApproachEntity extends ParentEntity<Integer> {

		@Column(name = "iteration")
		private Integer reiterationCount;

		@ManyToOne
		@NotNull
		@JoinColumn(name = "exercise_id", referencedColumnName = "id")
		private ExerciseEntity exercise;

}
