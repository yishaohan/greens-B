package com.ysh.projectY.service;

import com.ysh.projectY.dao.CitiesInfoDao;
import com.ysh.projectY.entity.CitiesInfo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CitiesInfoService {

    final CitiesInfoDao citiesInfoDao;

    public CitiesInfoService(CitiesInfoDao citiesInfoDao) {
        this.citiesInfoDao = citiesInfoDao;
    }

    public Optional<CitiesInfo> findById(int id) {
        return citiesInfoDao.findById(id);
    }

    public List<CitiesInfo> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "cityName");
        return citiesInfoDao.findAll(sort);
    }
}
