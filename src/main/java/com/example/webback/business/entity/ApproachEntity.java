package com.example.webback.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproachEntity extends ParentEntity<Integer> {

		private Integer reiterationCount;

}
