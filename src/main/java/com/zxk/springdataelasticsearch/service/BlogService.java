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
        PageModel pageModel = new PageModel(pageIndex,pageSize);
        Page<BlogModel> blogModelPage = blogRepository.findAll(pageModel);
        return Result.ok(blogModelPage);
    }


    public Result<BlogModel> findById(Long id){
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
        if (!StringUtils.isEmpty(blogModel.getId())){
            Result<BlogModel> result = findById(blogModel.getId());
            if (result.isSuccess() && result.getResult()!=null){
                blogRepository.save(blogModel);
                return Result.ok("更新成功",blogModel.getId());
            }else {
                return result;
            }
        }
        return Result.error("参数不正确");
    }

    public Result deleteById(Long id){
        if (StringUtils.isEmpty(id)){
            return Result.error("参数不正确");
        }
        Result<BlogModel> result = findById(id);
        if (result.isSuccess() && result.getResult()!=null){
            blogRepository.deleteById(id);
            return Result.ok("删除成功",id);
        }
        return result;
    }


    public Result<List<BlogModel>> findTitle(String search){
        if (StringUtils.isEmpty(search)){
            return Result.error("查询参数不正确");
        }
        List<BlogModel> list = blogRepository.findTitleLike(search);
        return Result.ok("查询成功",list);
    }

    public Result<Page<BlogModel>> pageTitle(String search,int pageIndex,int pageSize){
        if (StringUtils.isEmpty(search)){
            return Result.error("分页查询参数不正确");
        }
        if (StringUtils.isEmpty(pageIndex)){
            pageIndex = 0;
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        PageModel pageModel = new PageModel(pageIndex,pageSize);
        Page<BlogModel> page = blogRepository.pageTitleLike(search,pageModel);
        return Result.ok("分页查询成功",page);
    }


    public Result<Page<BlogModel>> pageContent(String search,int pageIndex,int pageSize){
        if (StringUtils.isEmpty(search)){
            return Result.error("分页查询参数不正确");
        }
        if (StringUtils.isEmpty(pageIndex)){
            pageIndex = 0;
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        PageModel pageModel = new PageModel(pageIndex,pageSize);
        Page<BlogModel> page = blogRepository.pageContentLike(search,pageModel);
        return Result.ok("分页查询成功",page);
    }

    public Result<Page<BlogModel>> querySearch(String search,int pageIndex,int pageSize){
        if (StringUtils.isEmpty(search)){
            return Result.error("查询参数不能为空");
        }
        if (StringUtils.isEmpty(pageIndex)){
            pageIndex = 0;
        }
        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }

        PageModel pageModel = new PageModel(pageIndex,pageSize);
        Page<BlogModel> page = blogRepository.pageLike(search,pageModel);
        return Result.ok("分页查询成功",page);
    }
}
