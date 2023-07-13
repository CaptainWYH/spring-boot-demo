package com.wyhcode.strategy.context;

import com.wyhcode.strategy.enums.PayEnum;
import com.wyhcode.strategy.handler.AlibabaPay;
import com.wyhcode.strategy.handler.PayHandler;
import com.wyhcode.strategy.handler.WxPay;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author weiyuhui
 * @date 2023/7/13 9:46
 * @description
 */

public class PayContext {

    private static Map<PayEnum,PayHandler> payEnumPayHandlerMap;

    static {
        payEnumPayHandlerMap = new HashMap<>();
        payEnumPayHandlerMap.put(PayEnum.ZFB,new AlibabaPay());
        payEnumPayHandlerMap.put(PayEnum.WX,new WxPay());
    }

    public static PayHandler getPayHandler(PayEnum payEnum){
        return payEnumPayHandlerMap.get(payEnum);
    }

}
