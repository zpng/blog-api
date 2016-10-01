/**
 * @(#)IPStorageImpl.java, 十月 01, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.storage;

import com.sanks.blog.server.data.IPAnalyze;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Collections;

/**
 * @author ldap
 */
@Repository
public class IPStorageImpl implements IPStorage {


    private static final RowMapper<IPAnalyze> IP_ANALYZE_ROWMAPPER = (resultSet, i) -> {
        IPAnalyze ipAnalyze = new IPAnalyze();
        ipAnalyze.setIp(resultSet.getString("ip"));
        ipAnalyze.setQueryCount(resultSet.getInt("queryCount"));
        ipAnalyze.setGetCount(resultSet.getInt("getCount"));
        ipAnalyze.setUpdateCount(resultSet.getInt("updateCount"));
        ipAnalyze.setCreateCount(resultSet.getInt("createCount"));
        ipAnalyze.setCreatedTime(resultSet.getLong("createdTime"));
        ipAnalyze.setUpdatedTime(resultSet.getLong("updatedTime"));
        return ipAnalyze;
    };

    @Autowired
    private NamedParameterJdbcOperations db;

    @Override
    public IPAnalyze get(String ip) {
        try {
            return db.queryForObject("select * from ip_analyze where ip = :ip",
                    Collections.singletonMap("ip", ip), IP_ANALYZE_ROWMAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void add(IPAnalyze ipAnalyze) {
        long now = System.currentTimeMillis();
        ipAnalyze.setCreatedTime(now);
        ipAnalyze.setUpdatedTime(now);
        SqlParameterSource parameter = new BeanPropertySqlParameterSource(ipAnalyze);
        db.update("insert into ip_analyze set ip = :ip, queryCount = :queryCount, getCount = " +
                ":getCount, updateCount = :updateCount, createCount = :createCount, createdTime =" +
                ":createdTime, updatedTime = :updatedTime", parameter);
    }

    @Override
    public void update(IPAnalyze ipAnalyze) {
        long now = System.currentTimeMillis();
        ipAnalyze.setUpdatedTime(now);
        SqlParameterSource parameter = new BeanPropertySqlParameterSource(ipAnalyze);
        db.update("update ip_analyze set queryCount = :queryCount, getCount = " +
                        ":getCount, updateCount = :updateCount, createCount = :createCount,updatedTime = :updatedTime where ip = :ip",
                parameter);

    }
}
