package com.zxk.springdataelasticsearch.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: spring-data-elasticsearch
 * @description: 结果类
 * @author: Zhangxike
 * @create: 2020-09-15 15:37
 **/
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -6992387638304881784L;

    private boolean success = true;

    private String message;

    private Integer code = 0;

    private T result;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp = new Date();

    public Result() {
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
        if (success){
            this.code = 200;
        }else{
            this.code = 500;
        }
    }

    public Result(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
        if (success){
            this.code = 200;
        }else{
            this.code = 500;
        }
    }

    public void error500(String message) {
        this.message = message;
        this.code = 500;
        this.success = false;
    }

    public void success(String message) {
        this.message = message;
        this.code = 200;
        this.success = true;
    }

    public static <T> Result<T> error(String msg) {
        return error(500, msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static <T> Result<T> ok() {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    public static <T> Result<T> ok(String msg) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage(msg);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.setSuccess(true);
        r.setCode(200);
        r.setResult(data);
        return r;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
