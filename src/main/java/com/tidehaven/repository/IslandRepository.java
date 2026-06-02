package com.tidehaven.repository;

import com.tidehaven.model.Island;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IslandRepository extends JpaRepository<Island, Long> {
    Optional<Island> findByUserId(Long userId);
}