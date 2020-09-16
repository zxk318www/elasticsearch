package com.zxk.springdataelasticsearch.service;

import com.zxk.springdataelasticsearch.model.BlogModel;
import com.zxk.springdataelasticsearch.model.PageModel;
import com.zxk.springdataelasticsearch.model.Result;
import com.zxk.springdataelasticsearch.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: spring-data-elasticsearch
 * @description: service
 * @author: Zhangxike
 * @create: 2020-09-15 14:14
 **/
@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public Result save(BlogModel blogModel){
        if (StringUtils.isEmpty(blogModel.getContent()) || StringUtils.isEmpty(blogModel.getTitle())){
            return Result.error("参数不正确");
        }
        blogRepository.save(blogModel);
        return Result.ok("保存成功");
    }

    public Result<List<BlogModel>> getAll(){
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return Result.ok(list);
    }

    public Result<Page<BlogModel>> pageQuery(Integer pageIndex,Integer pageSize){
        if (pageIndex == null ){
            pageIndex = 0;
        }
        if (pageSize == null){
            pageSize = 10;
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageNumber(pageIndex);
        pageModel.setPageSize(pageSize);
        Page<BlogModel> blogModelPage = blogRepository.findAll(pageModel);
        return Result.ok(blogModelPage);
    }


    public Result<BlogModel> findById(String id){
        if (StringUtils.isEmpty(id)){
            return Result.error("参数不正确");
        }
        Optional<BlogModel> byId = blogRepository.findById(id);
        if (byId.isPresent()){
            BlogModel blogModel = byId.get();
            return Result.ok(blogModel);
        }
        return Result.ok("未查询到数据");
    }


    public Result updateById(BlogModel blogModel){
        if (blogModel.getId()!=null){
            Result<BlogModel> result = findById(String.valueOf(blogModel.getId()));
            if (result.isSuccess() && result.getResult()!=null){
                blogRepository.save(blogModel);
                return Result.ok("更新成功");
            }else {
                return result;
            }
        }
        return Result.error("参数不正确");
    }

    public Result deleteById(String id){
        if (StringUtils.isEmpty(id)){
            return Result.error("参数不正确");
        }
        Result<BlogModel> result = findById(id);
        if (result.isSuccess() && result.getResult()!=null){
            blogRepository.deleteById(id);
            return Result.ok("删除成功");
        }
        return result;

    }
}
