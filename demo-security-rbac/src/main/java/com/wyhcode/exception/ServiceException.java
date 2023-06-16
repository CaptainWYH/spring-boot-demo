package com.wyhcode.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author weiyuhui
 * @date 2023/6/16 15:20
 * @description
 */

@Data
public class ServiceException extends RuntimeException{

    private Integer code;

    private String msg;

    public ServiceException(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
