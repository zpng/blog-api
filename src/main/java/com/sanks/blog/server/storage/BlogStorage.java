/**
 * @(#)BlogStorage.java, 九月 29, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.storage;

import com.sanks.blog.server.data.Blog;

import java.util.List;

/**
 * @author zhangpeng
 */
public interface BlogStorage {
    Blog get(int id);

    int create(Blog blog);

    List<Blog> query();
}
