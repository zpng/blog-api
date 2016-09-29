/**
 * @(#)BlogService.java, 九月 29, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.service;

import com.sanks.blog.server.data.Blog;
import com.sanks.blog.server.storage.BlogStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangpeng
 */
@Service
public class BlogService {

    @Autowired
    private BlogStorage blogStorage;

    public Blog create(Blog blog) {
        int id = blogStorage.create(blog);
        return blogStorage.get(id);
    }

    public Blog get(int id) {
        return blogStorage.get(id);
    }

    public List<Blog> query() {
        return blogStorage.query();
    }
}