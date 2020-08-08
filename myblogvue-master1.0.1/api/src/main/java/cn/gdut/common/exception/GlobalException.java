package cn.gdut.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Desctiption TODO
 * @Date 2019/11/17/017
 **/
public class GlobalException extends RuntimeException {

    @Getter
    @Setter
    private String msg;

    public GlobalException(String message){
        super(message);
        this.msg = message;
    }
}
