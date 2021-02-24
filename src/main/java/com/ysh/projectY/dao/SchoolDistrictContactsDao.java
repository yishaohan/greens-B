package com.ysh.projectY.dao;

import com.ysh.projectY.entity.SchoolDistrictContacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolDistrictContactsDao extends JpaRepository<SchoolDistrictContacts, Integer> {

}
