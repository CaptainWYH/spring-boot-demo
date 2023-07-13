package com.wyhcode.strategy.enums;

import com.wyhcode.strategy.handler.AlibabaPay;
import com.wyhcode.strategy.handler.PayHandler;
import com.wyhcode.strategy.handler.WxPay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @date 2023/7/12 18:04
 * @description
 */

@AllArgsConstructor
@NoArgsConstructor
public enum PayEnum {

    WX(1,"微信支付"),
    ZFB(2,"支付宝支付");

    private int code;

    private String name;

    public static PayEnum getPayEnum(int code){
        for (PayEnum value : PayEnum.values()) {
            if (code == value.code){
                return value;
            }
        }
        return null;
    }
}
