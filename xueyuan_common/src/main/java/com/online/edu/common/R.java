package com.online.edu.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//定义具体的数据返回格式
@Data
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private R() {}

    //操作成功，调用此方法
    public static R ok() {

        R r = new R();

        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");

        return r;
    }

    //操作失败，调用此方法
    public static R error() {

        R r = new R();

        r.setSuccess(false);
        r.setCode(ResultCode.EROOR);
        r.setMessage("操作失败");

        return r;
    }

    //链式编程
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R messsage(String messsage) {
        this.setMessage(messsage);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key,Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }
}
