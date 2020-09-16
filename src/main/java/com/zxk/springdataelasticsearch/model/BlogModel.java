package com.zxk.springdataelasticsearch.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @program: spring-data-elasticsearch
 * @description: ElasticSearch Document文档类
 * @author: Zhangxike
 * @create: 2020-09-15 14:12
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "hello-index")
public class BlogModel implements Serializable {
    private static final long serialVersionUID = -1933808051190771324L;

    private Long id;

    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String content;
}
