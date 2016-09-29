/**
 * @(#)BlogStorageImpl.java, 九月 29, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.storage;

import com.sanks.blog.server.data.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author zhangpeng
 */
@Repository
public class BlogStorageImpl implements BlogStorage {

    private static final RowMapper<Blog> BLOG_ROW_MAPPER = (resultSet, i) -> {
        Blog blog = new Blog();
        blog.setId(resultSet.getInt("id"));
        blog.setAuthorId(resultSet.getInt("authorId"));
        blog.setTitle(resultSet.getString("title"));
        blog.setContent(resultSet.getString("content"));
        blog.setCreatedTime(resultSet.getLong("createdTime"));
        blog.setUpdatedTime(resultSet.getLong("updatedTime"));
        return blog;
    };
    @Autowired
    private NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public Blog get(int id) {
        return jdbcTemplate.queryForObject("select * from blog where id = :id", Collections.singletonMap("id", id),
                BLOG_ROW_MAPPER);
    }

    @Override
    public int create(Blog blog) {
        long now = System.currentTimeMillis();
        blog.setCreatedTime(now);
        blog.setUpdatedTime(now);
        SqlParameterSource parameter = new BeanPropertySqlParameterSource(blog);
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into blog set authorId = :authorId, title = :title, content = :content, " +
                "createdTime = :createdTime, updatedTime = :updatedTime", parameter, keyholder);
        return keyholder.getKey().intValue();
    }

    @Override
    public List<Blog> query() {
        return jdbcTemplate.query("select * from blog", BLOG_ROW_MAPPER);
    }
}