package com.ysh.projectY.controller;

import com.ysh.projectY.entity.BCSchoolsCOVID19;
import com.ysh.projectY.service.BCSchoolsCOVID19Service;
import com.ysh.projectY.utils.JsonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class BCSchoolsCOVID19Controller {

    final BCSchoolsCOVID19Service bcSchoolsCOVID19Service;

    public BCSchoolsCOVID19Controller(BCSchoolsCOVID19Service bcSchoolsCOVID19Service) {
        this.bcSchoolsCOVID19Service = bcSchoolsCOVID19Service;
    }

    // 匿名用户可以访问
    @GetMapping("/public/bcSchoolsCOVID19")
    public HttpEntity<?> getBCSchoolsCOVID19(@RequestParam(name = "schoolName", defaultValue = "") String schoolName,
                                             @RequestParam(name = "notificationDate", defaultValue = "") String notificationDate,
                                             @RequestParam(name = "districtAbb", defaultValue = "") String districtAbb,
                                             @RequestParam(name = "notificationMethod", defaultValue = "") String notificationMethod,
                                             @RequestParam(name = "healthRegionName", defaultValue = "") String healthRegionName,
                                             @RequestParam(name = "cityName", defaultValue = "") String cityName,
                                             @RequestParam(name = "current", defaultValue = "1") int current,
                                             @RequestParam(name = "pageSize", defaultValue = "20") int pageSize) {
        if (current <= 0) {
            current = 1;
        }
        if (pageSize <= 0) {
            pageSize = 1;
        }
        if (pageSize > 1000) {
            pageSize = 1000;
        }
        System.out.println(notificationDate);
        if (!"".equals(notificationDate) && notificationDate.length() >= 10) {
            notificationDate = notificationDate.substring(0, 10);
        }
        Pageable pageable = PageRequest.of(current - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        final Page<BCSchoolsCOVID19> bcSchoolsCOVID19 = bcSchoolsCOVID19Service.getBCSchoolsCOVID19(schoolName, notificationDate, districtAbb, notificationMethod, healthRegionName, cityName, pageable);
//        Map<String, Object> temp = new HashMap<>();
//        temp.put("bcSchoolsCOVID19DataType", bcSchoolsCOVID19);
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "projectY.RoleController.getRoles.success", bcSchoolsCOVID19, "bcSchoolsCOVID19.toString()"), HttpStatus.OK);
    }

}
