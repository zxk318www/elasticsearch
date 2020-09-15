package com.zxk.springdataelasticsearch.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @program: spring-data-elasticsearch
 * @description: 分页Model
 * @author: Zhangxike
 * @create: 2020-09-15 16:04
 **/
@Data
public class PageModel implements Pageable {

    private int pageNumber;

    private int pageSize;

    private long offset;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getOffset() {
        return this.offset;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
