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
    public Result<BlogModel> findById(Long id){
        return blogService.findById(id);
    }

    /**
     * 根据路径id查询 xxx/findById/{id}
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Result<BlogModel> findByPathId(@PathVariable Long id){
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
    public Result deleteById(Long id){
        System.out.println(id);
        return blogService.deleteById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteByPathId(@PathVariable  Long id){
        System.out.println(id);
        return blogService.deleteById(id);
    }


    /**
     * 根据标题模糊查询
     * @param search
     * @return
     */
    @GetMapping("/findTitle")
    public Result<List<BlogModel>> findTitle(String search){
        System.out.println(search);
        return blogService.findTitle(search);
    }

    @GetMapping("/findTitle/{search}")
    public Result<List<BlogModel>> findPathTitle(@PathVariable String search){
        System.out.println(search);
        return blogService.findTitle(search);
    }


    /**
     * 根据标题分页模糊查询
     * @param search
     * @return
     */
    @GetMapping("/pageTitle")
    public Result<Page<BlogModel>> pageTitle(String search,int pageIndex,int pageSize){
        System.out.println(search);
        return blogService.pageTitle(search,pageIndex,pageSize);
    }

    @GetMapping("/pageTitle/{search}/{pageIndex}/{pageSize}")
    public Result<Page<BlogModel>> pagePathTitle(@PathVariable String search,@PathVariable int pageIndex,@PathVariable int pageSize){
        System.out.println(search);
        return blogService.pageTitle(search,pageIndex,pageSize);
    }
}
