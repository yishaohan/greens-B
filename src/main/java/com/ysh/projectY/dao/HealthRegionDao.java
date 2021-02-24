package com.ysh.projectY.dao;

import com.ysh.projectY.entity.HealthRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthRegionDao extends JpaRepository<HealthRegion, Integer> {

    public Optional<HealthRegion> findByHealthRegionName(String healthRegionName);
}
