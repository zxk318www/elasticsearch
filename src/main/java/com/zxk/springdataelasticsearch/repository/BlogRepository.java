package com.zxk.springdataelasticsearch.repository;

import com.zxk.springdataelasticsearch.model.BlogModel;
import com.zxk.springdataelasticsearch.model.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @program: spring-data-elasticsearch
 * @description: 文档查询接口类
 * @author: Zhangxike
 * @create: 2020-09-15 14:13
 **/
public interface BlogRepository extends ElasticsearchRepository<BlogModel,Long> {
    /**
     * 根据查询值  查询标题
     * @param search
     * @return
     */
    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<BlogModel> findTitleLike(String search);

    /**
     * 根据查询   分页查询标题
     * @param search
     * @param pageModel
     * @return
     */
    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    Page<BlogModel> pageTitleLike(String search, PageModel pageModel);

    /**
     * 根据查询  分页查询内容
     * @param search
     * @param pageModel
     * @return
     */
    @Query("{\"match_phrase\":{\"content\":\"?0\"}}")
    Page<BlogModel> pageContentLike(String search, PageModel pageModel);

    /**
     * 带分析模糊查询标题和内容
     * @param search
     * @param pageModel
     * @return
     */
    @Query("{\"query_string\":{\"query\":\"?0\",\"fields\": [\"content\",\"title\"]}}")
    Page<BlogModel> pageLike(String search, PageModel pageModel);

}
