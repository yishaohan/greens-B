package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.form.CreateMenu;
import com.ysh.projectY.form.UpdateMenu;
import com.ysh.projectY.service.BCSchoolsCOVID19Service;
import com.ysh.projectY.service.MenuService;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.utils.MethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class BCSchoolsCOVID19Controller {

    final BCSchoolsCOVID19Service bcSchoolsCOVID19Service;

    public BCSchoolsCOVID19Controller(BCSchoolsCOVID19Service bcSchoolsCOVID19Service) {
        this.bcSchoolsCOVID19Service = bcSchoolsCOVID19Service;
    }

    // 匿名用户可以访问
    @GetMapping("/public/bcSchoolsCOVID19")
    public HttpEntity<?> getBCSchoolsCOVID19() {
        return null;
    }

}
