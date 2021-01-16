package com.ysh.projectY.dao;

import com.ysh.projectY.entity.RememberMeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RememberMeTokenDao extends JpaRepository<RememberMeToken, Integer> {

    @Query(value = "select id,username, series, token, last_used_date_time from project_y.remember_me_token where series=:series", nativeQuery = true)
    RememberMeToken getTokenForSeries(String series);

    //    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Modifying
    @Query(value = "insert into project_y.remember_me_token(username, series, token, last_used_date_time) values(:username, :series, :token, :date)", nativeQuery = true)
    void createNewToken(String username, String series, String token, Date date);

    //    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Modifying
    @Query(value = "update project_y.remember_me_token set token=:token, last_used_date_time=:lastUsedDateTime where series=:series", nativeQuery = true)
    void updateToken(String series, String token, Date lastUsedDateTime);

    //    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Modifying
    @Query(value = "delete from project_y.remember_me_token where username=:username", nativeQuery = true)
    void removeUserTokens(String username);
}
