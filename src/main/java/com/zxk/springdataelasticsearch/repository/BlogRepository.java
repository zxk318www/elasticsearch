package com.zxk.springdataelasticsearch.repository;

import com.zxk.springdataelasticsearch.model.BlogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @program: spring-data-elasticsearch
 * @description: 文档查询接口类
 * @author: Zhangxike
 * @create: 2020-09-15 14:13
 **/
public interface BlogRepository extends ElasticsearchRepository<BlogModel,String> {
}
