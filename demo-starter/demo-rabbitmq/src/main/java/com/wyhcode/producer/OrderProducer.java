package com.wyhcode.producer;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.wyhcode.bean.Order;
import com.wyhcode.config.RabbitMqConfig;
import com.wyhcode.enums.OrderEnum;
import com.wyhcode.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author weiyuhui
 * @date 2023/7/14 14:38
 * @description
 */

@Slf4j
@Component
public class OrderProducer {

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createOrder(int i){
        // 创建订单    订单状态默认未支付
        Order order = new Order();
        order.setId(i);
        order.setStatus(OrderEnum.NO_PAY.getType());
        orderMapper.insertOrder(order);
        // 发送消息到消息队列
        log.info("插入新订单数据，向订单队列发送一条延迟时间为30秒的信息");
        rabbitTemplate.convertAndSend(RabbitMqConfig.ORDER_EXCHANGE,RabbitMqConfig.ORDER_ROUTING, JSON.toJSONString(order));

    }

}
