package com.example.webback.business.dao;

import com.example.webback.business.entity.WorkoutsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutsRepository extends JpaRepository<WorkoutsEntity, Integer> {
}