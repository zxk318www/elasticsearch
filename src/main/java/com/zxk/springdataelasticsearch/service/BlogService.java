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
}
