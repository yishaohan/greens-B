package com.ysh.projectY.dao;

import com.ysh.projectY.entity.BCSchoolsCOVID19TempRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BCSchoolsCOVID19TempRecordDao extends JpaRepository<BCSchoolsCOVID19TempRecord, Integer> {

    @Query(value = "select count(*) from project_y._temp_bc_school_covid19", nativeQuery = true)
    public int findAllCount();

    @Query(value = "select * from project_y._temp_bc_school_covid19 where id = (select min(id) from project_y._temp_bc_school_covid19 where status!='Imported')", nativeQuery = true)
    public Optional<BCSchoolsCOVID19TempRecord> findByMinId();
}
