package com.example.webback.business.entity;


import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

		private Integer id;

		private String firstName;

		private String secondName;

		private String photoUri;

		private Integer growth;

		private Double weight;

}
