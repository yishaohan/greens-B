package com.ysh.projectY.dao;

import com.ysh.projectY.entity.Menu;
import com.ysh.projectY.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {

    Optional<Menu> findByMenuName(String menuName);

    Page<Menu> findAllByMenuNameContainingAndMenuDescriptContainingAndMenuPathContaining(String menuName, String menuDescript, String menuPath, Pageable pageable);

    List<Menu> findByMenuGradeLessThan(int menuGrade);
}
