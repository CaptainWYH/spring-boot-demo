package com.wyhcode.exception;

import com.wyhcode.common.BaseException;
import com.wyhcode.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weiyuhui
 * @date 2023/6/14 14:03
 * @description
 */

@EqualsAndHashCode(callSuper = true)
public class SecurityException extends BaseException {
    public SecurityException(StatusEnum status) {
        super(status);
    }

    public SecurityException(StatusEnum status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
