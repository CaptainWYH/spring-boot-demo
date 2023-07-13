package com.wyhcode.strategy.handler;

import com.wyhcode.strategy.context.PayContext;
import com.wyhcode.strategy.enums.PayEnum;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:59
 * @description
 */

public class WxPay implements PayHandler{

    @Override
    public void pay() {
        System.out.println("微信支付");
    }

}
