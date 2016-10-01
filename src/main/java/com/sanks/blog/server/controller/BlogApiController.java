/**
 * @(#)BlogApiController.java, 九月 29, 2016.
 * <p>
 * Copyright 2016 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sanks.blog.server.controller;

import com.sanks.blog.server.data.Blog;
import com.sanks.blog.server.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangpeng
 */
@RestController
@RequestMapping("/sanks-blog/api/blogs")
public class BlogApiController {

    @Autowired
    private BlogService blogService;


    @RequestMapping()
    @CrossOrigin(origins = "http://localhost:3000")
    List<Blog> query(){
        return blogService.query();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method = RequestMethod.POST)
    Blog create(@RequestBody Blog blog) {
        return blogService.create(blog);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    Blog update(@PathVariable int id, @RequestBody Blog blog) {
        //todo id check
        return blogService.update(blog);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/{id}")
    Blog get(@PathVariable int id) {
        return blogService.get(id);
    }

}