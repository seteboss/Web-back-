package com.example.webback.business.dao;

import com.example.webback.business.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<UserEntity>, JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String userName);
    List<UserEntity> findAllByEnabledTrue();
}
