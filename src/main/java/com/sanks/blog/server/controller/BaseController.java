/**
 * @(#)BaseController.java, 十月 01, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ldap
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    protected String getIp(){
        return request.getRemoteAddr();
    }
}
