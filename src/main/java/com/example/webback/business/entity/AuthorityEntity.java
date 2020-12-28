package com.example.webback.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@Table(name = "authority")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@ToString
public class AuthorityEntity extends Model<Integer> implements GrantedAuthority {

		private String description;

		@Column(name = "date_time_create")
		@CreatedDate
		private LocalDateTime dateTimeCreate;

		@Column(name = "date_time_modif")
		@LastModifiedDate
		private LocalDateTime dateTimeModif;

		@Override
		@JsonIgnore
		public String getAuthority() {
				return getName();
		}

}
