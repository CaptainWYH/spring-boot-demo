package com.wyhcode.mapper;

import com.wyhcode.bean.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weiyuhui
 * @date 2023/7/14 14:38
 * @description
 */

@Component
public class OrderMapper {

    /**
     * 内存数据
     */
    public static Map<Integer, Order> orderMap;

    static {
        orderMap = new ConcurrentHashMap<>();
    }


    /**
     * 新增一个订单
     * @param order
     */
    public void insertOrder(Order order){
        orderMap.put(order.getId(),order);
    }



}
