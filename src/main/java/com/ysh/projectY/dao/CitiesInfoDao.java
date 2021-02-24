package com.ysh.projectY.dao;

import com.ysh.projectY.entity.CitiesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesInfoDao extends JpaRepository<CitiesInfo, Integer> {
}
