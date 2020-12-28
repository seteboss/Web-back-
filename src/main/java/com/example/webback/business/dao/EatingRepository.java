package com.example.webback.business.dao;

import com.example.webback.business.entity.EatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EatingRepository extends JpaRepository<EatingEntity, Integer> {
}
