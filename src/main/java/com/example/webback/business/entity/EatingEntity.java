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
@Table(name = "eating")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EatingEntity extends Model<Integer>{

		@Column(name = "info")
		private String info;

		@ManyToOne
		@NotNull
		@JoinColumn(name = "diets_id", referencedColumnName = "id")
		private DietsEntity diets;
}
