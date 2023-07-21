package com.wyhcode.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.wyhcode.bean.Order;
import com.wyhcode.config.RabbitMqConfig;
import com.wyhcode.enums.OrderEnum;
import com.wyhcode.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author weiyuhui
 * @date 2023/7/14 14:22
 * @description
 */

@Slf4j
@Component
public class OrderConsumer {

    @RabbitListener(queues = RabbitMqConfig.ORDER_DEAD_QUEUE)
    public void onMessage(Message message, Channel channel) throws IOException {
        Order order = JSONObject.parseObject(new String(message.getBody()), Order.class);
        log.info("订单进入死信队列:{}",order);
//        log.info("订单Id {}",order.getId());
        // 查询订单状态
        if (order.getStatus() != null && !order.getStatus().equals(OrderEnum.READY_PAY.getType())){
            order.setStatus(OrderEnum.OVER_PAY.getType());
            OrderMapper.orderMap.put(order.getId(),order );
            log.info("修改后的订单状态:{}",order);
        }

        // 手动应答
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
