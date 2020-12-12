package com.example.webback.business.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParentEntity<T extends Serializable> {
    T id;
}
