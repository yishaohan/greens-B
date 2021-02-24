package com.ysh.projectY.service;

import com.ysh.projectY.dao.HealthRegionDao;
import com.ysh.projectY.entity.HealthRegion;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HealthRegionService {

    final HealthRegionDao healthRegionDao;

    public HealthRegionService(HealthRegionDao healthRegionDao) {
        this.healthRegionDao = healthRegionDao;
    }

    public Optional<HealthRegion> findByHealthRegionName(String healthRegionName) {
        return healthRegionDao.findByHealthRegionName(healthRegionName);
    }

    public List<HealthRegion> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "healthRegionName");
        return healthRegionDao.findAll(sort);
    }

    public Optional<HealthRegion> findById(int id) {
        return healthRegionDao.findById(id);
    }
}
