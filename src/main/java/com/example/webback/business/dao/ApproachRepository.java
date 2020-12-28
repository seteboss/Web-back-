package com.example.webback.business.dao;

import com.example.webback.business.entity.ApproachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproachRepository extends JpaRepository<ApproachEntity, Integer> {
}
