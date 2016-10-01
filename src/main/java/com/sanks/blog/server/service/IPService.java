/**
 * @(#)IPService.java, 十月 01, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.service;

import com.sanks.blog.server.data.IPAnalyze;
import com.sanks.blog.server.storage.IPStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ldap
 */
@Service
public class IPService {

    @Autowired
    private IPStorage ipStorage;

    public void addCount(String ip, int queryCount, int getCount, int createCount, int
            updateCount) {
        IPAnalyze ipAnalyze = ipStorage.get(ip);
        if (null == ipAnalyze){
            ipAnalyze = new IPAnalyze();
            ipAnalyze.setIp(ip);
            ipAnalyze.setQueryCount(queryCount);
            ipAnalyze.setGetCount(getCount);
            ipAnalyze.setCreateCount(createCount);
            ipAnalyze.setUpdateCount(updateCount);
            ipStorage.add(ipAnalyze);
        }else {
            ipAnalyze.setQueryCount(ipAnalyze.getQueryCount() + queryCount);
            ipAnalyze.setGetCount(ipAnalyze.getGetCount() + getCount);
            ipAnalyze.setCreateCount(ipAnalyze.getCreateCount() + createCount);
            ipAnalyze.setUpdateCount(ipAnalyze.getUpdateCount() + updateCount);
            ipStorage.update(ipAnalyze);
        }
    }
}
