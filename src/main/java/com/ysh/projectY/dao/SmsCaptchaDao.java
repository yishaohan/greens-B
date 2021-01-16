package com.ysh.projectY.dao;

import com.ysh.projectY.entity.SmsCaptcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmsCaptchaDao extends JpaRepository<SmsCaptcha, Integer> {

    List<SmsCaptcha> findByMobilePhoneAndStatus(String mobilePhone, String status);

    @Query(value = "select * from project_y.sms_captcha where id = (select max(id) from project_y.sms_captcha where status = 'CREATED' and mobile_phone =:mobilePhone)", nativeQuery = true)
    Optional<SmsCaptcha> findLastValidSmsCaptchaByMobilePhone(String mobilePhone);

    List<SmsCaptcha> findBySourceAndClientIpAndCreateDateTimeGreaterThan(String source, String clientIp, Timestamp timestamp);
}
