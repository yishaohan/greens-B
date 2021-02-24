package com.ysh.projectY.dao;

import com.ysh.projectY.entity.SchoolDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolDistrictDao extends JpaRepository<SchoolDistrict, Integer> {


}
