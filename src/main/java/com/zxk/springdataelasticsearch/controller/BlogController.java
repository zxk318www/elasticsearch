package com.zxk.springdataelasticsearch.controller;

import com.zxk.springdataelasticsearch.model.BlogModel;
import com.zxk.springdataelasticsearch.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-data-elasticsearch
 * @description: Controller
 * @author: Zhangxike
 * @create: 2020-09-15 14:16
 **/
@RestController
@RequestMapping("/")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/add")
    public String addBlog(@RequestBody BlogModel blogModel){
        System.out.println(blogModel);
        return blogService.save(blogModel);
    }
}
