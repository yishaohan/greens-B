package com.ysh.projectY.service;

import com.ysh.projectY.dao.SchoolDistrictDao;
import com.ysh.projectY.entity.SchoolDistrict;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SchoolDistrictService {

    final SchoolDistrictDao schoolDistrictDao;

    public SchoolDistrictService(SchoolDistrictDao schoolDistrictDao) {
        this.schoolDistrictDao = schoolDistrictDao;
    }

    public void saveAndFlush(SchoolDistrict schoolDistrict) {
        schoolDistrictDao.saveAndFlush(schoolDistrict);
    }

    public Optional<SchoolDistrict> findById(int id) {
        return schoolDistrictDao.findById(id);
    }

    public void delete(SchoolDistrict schoolDistrict) {
        schoolDistrictDao.delete(schoolDistrict);
    }

    public List<SchoolDistrict> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "districtName");
        return schoolDistrictDao.findAll(sort);
    }
}
