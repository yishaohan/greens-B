package com.ysh.projectY.service;

import com.ysh.projectY.dao.BCSchoolsCOVID19TempRecordDao;
import com.ysh.projectY.entity.BCSchoolsCOVID19TempRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BCSchoolsCOVID19TempRecordService {

    final BCSchoolsCOVID19TempRecordDao bcSchoolsCOVID19TempRecordDao;

    public BCSchoolsCOVID19TempRecordService(BCSchoolsCOVID19TempRecordDao bcSchoolsCOVID19TempRecordDao) {
        this.bcSchoolsCOVID19TempRecordDao = bcSchoolsCOVID19TempRecordDao;
    }

    public int findAllCount() {
        return bcSchoolsCOVID19TempRecordDao.findAllCount();
    }

    public Optional<BCSchoolsCOVID19TempRecord> findByMinId() {
        return bcSchoolsCOVID19TempRecordDao.findByMinId();
    }

    public void saveAndFlush(BCSchoolsCOVID19TempRecord bcSchoolsCOVID19TempRecord) {
        bcSchoolsCOVID19TempRecordDao.saveAndFlush(bcSchoolsCOVID19TempRecord);
    }
}
