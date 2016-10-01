/**
 * @(#)IPStorage.java, 十月 01, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.storage;

import com.sanks.blog.server.data.IPAnalyze;

/**
 * @author ldap
 */
public interface IPStorage {

    IPAnalyze get(String ip);

    void add(IPAnalyze ipAnalyze);

    void update(IPAnalyze ipAnalyze);
}
