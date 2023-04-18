package com.CGMspringboot.domain.repository;

import com.CGMspringboot.domain.entity.GlucoseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlucoseLevelRepository extends JpaRepository<GlucoseLevel, Integer> {
}
