package com.fan.system.exception;

import lombok.Getter;

/**
 * @date: 2022/11/28 - 05 - 08 - 17:33
 * @version: 1.0
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
