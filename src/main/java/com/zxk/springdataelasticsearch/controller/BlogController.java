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
     *  翻页查询 xxx/page?pageIndex={pageIndex}&pageSize={pageSize}
     * @param pageIndex 页数
     * @param pageSize 每页查询数量
     * @return
     */
    @GetMapping("/page")
    public Result<Page<BlogModel>> pageQuery(Integer pageIndex,Integer pageSize){
        return blogService.pageQuery(pageIndex,pageSize);
    }

    /**
     * 分页查询，路径参数 xxx/page/{pageIndex}/{pageSize}
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{pageIndex}/{pageSize}")
    public Result<Page<BlogModel>> pagePathValueQuery(@PathVariable Integer pageIndex,@PathVariable Integer pageSize){
        return blogService.pageQuery(pageIndex, pageSize);
    }

    /**
     * 根据id查询 xxx/findById?id={id}
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result<BlogModel> findById(String id){
        return blogService.findById(id);
    }

    /**
     * 根据路径id查询 xxx/findById/{id}
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result<BlogModel> findByPathId(@PathVariable String id){
        return blogService.findById(id);
    }

    /**
     * 根据id更新Document
     * @param blogModel
     * @return
     */
    @PostMapping("/updateById")
    public Result updateById(@RequestBody BlogModel blogModel){
        System.out.println(blogModel);
        return blogService.updateById(blogModel);
    }

    /**
     * 根据id删除document
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result deleteById(String id){
        System.out.println(id);
        return blogService.deleteById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteByPathId(@PathVariable  String id){
        System.out.println(id);
        return blogService.deleteById(id);
    }

}
