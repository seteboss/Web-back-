package com.example.webback.business.entity;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Model<T extends Serializable> extends ParentEntity<T> {
    String name;
}
