package com.wyhcode.strategy.handler;

import com.wyhcode.strategy.context.PayContext;
import com.wyhcode.strategy.enums.PayEnum;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:54
 * @description
 */

public class AlibabaPay implements PayHandler{
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }


}
