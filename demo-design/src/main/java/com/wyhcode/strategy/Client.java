package com.wyhcode.strategy;

import com.wyhcode.strategy.context.PayContext;
import com.wyhcode.strategy.enums.PayEnum;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:52
 * @description
 */

public class Client {
    public static void main(String[] args) {
        PayContext.getPayHandler(PayEnum.ZFB).pay();
    }
}
