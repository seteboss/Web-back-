package com.example.webback.business.dao;

import com.example.webback.business.entity.DietsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietsRepository extends JpaRepository<DietsEntity, Integer> {
}
