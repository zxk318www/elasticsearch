package com.zxk.springdataelasticsearch.controller;

import com.zxk.springdataelasticsearch.model.BlogModel;
import com.zxk.springdataelasticsearch.model.Result;
import com.zxk.springdataelasticsearch.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result addBlog(@RequestBody BlogModel blogModel){
        System.out.println(blogModel);
        return blogService.save(blogModel);
    }

    /**
     * 获取全部数据
     * @return
     */
    @GetMapping("/get")
    public Result<List<BlogModel>> getAll(){
       return blogService.getAll();
    }

    /**
     *  翻页查询
     * @param pageIndex 页数
     * @param pageSize 每页查询数量
     * @return
     */
    @GetMapping("/page")
    public Result<Page<BlogModel>> pageQuery(Integer pageIndex,Integer pageSize){
        return blogService.pageQuery(pageIndex,pageSize);
    }
}
