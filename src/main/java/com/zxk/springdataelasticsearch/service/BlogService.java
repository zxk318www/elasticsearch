package com.zxk.springdataelasticsearch.service;

import com.zxk.springdataelasticsearch.model.BlogModel;
import com.zxk.springdataelasticsearch.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String save(BlogModel blogModel){
        blogRepository.save(blogModel);
        return "success";
    }
}
