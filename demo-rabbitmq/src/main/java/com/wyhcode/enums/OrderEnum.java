package com.wyhcode.enums;

import lombok.Getter;

/**
 * @author weiyuhui
 * @date 2023/7/14 14:41
 * @description
 */
@Getter
public enum OrderEnum {

    NO_PAY(0,"未支付"),
    READY_PAY(1,"已支付"),
    OVER_PAY(2,"取消订单");

    private Integer type;

    private String desc;

    OrderEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
