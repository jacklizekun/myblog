package cn.gdut.common.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Desctiption TODO
 * @Date 2019/11/17/017
 **/
public class R<T> implements Serializable {
    @Getter
    @Setter
    private int code = 200;

    @Getter
    @Setter
    private Object msg = "success";

    @Setter
    @Getter
    private T data;

    public R (){
        super();
    }

    public R(T data){
        super();
        this.data = data;
    }

    public R(Object msg, T data) {
        this.msg = msg;
        this.data = data;
    }
}
