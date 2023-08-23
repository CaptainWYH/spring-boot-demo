package com.wyhcode.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author weiyuhui
 * @date 2023/7/14 11:35
 * @description
 */

@Configuration
public class RabbitMqConfig {
    /**
     * 订单队列
     */
    public static final String ORDER_QUEUE = "order_queue";

    /**
     * 订单交换机
     */
    public static final String ORDER_EXCHANGE = "order_exchange";

    /**
     * 死信交换机
     */
    public static final String ORDER_DEAD_EXCHANGE = "order_dead_exchange";

    /**
     * 死信队列
     */
    public static final String ORDER_DEAD_QUEUE = "order_dead_queue";

    /**
     * 订单routing
     */
    public static final String ORDER_ROUTING = "order_routing";

    /**
     * 超时订单routing
     */
    public static final String ORDER_DEAD_ROUTING = "order_dead_routing";

    @Bean("orderExchange")
    public DirectExchange orderExchange(){
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean("orderDeadExchange")
    public DirectExchange orderDeadExchange(){
        return new DirectExchange(ORDER_DEAD_EXCHANGE);
    }

    @Bean("orderQueue")
    public Queue orderQueue(){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange",ORDER_DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", ORDER_DEAD_ROUTING);
        map.put("x-message-ttl",30000);
        return QueueBuilder.durable(ORDER_QUEUE).withArguments(map).build();

    }

    @Bean("orderDeadQueue")
    public Queue orderDeadQueue(){
        return new Queue(ORDER_DEAD_QUEUE,true,false,false,null);
    }

    @Bean
    public Binding orderExchangeBindOrderQueue(@Qualifier("orderExchange")DirectExchange orderExchange,
                                               @Qualifier("orderQueue")Queue orderQueue){
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ORDER_ROUTING);
    }

    @Bean
    public Binding deadExchangeDead(@Qualifier("orderDeadExchange")DirectExchange orderDeadExchange,
                                    @Qualifier("orderDeadQueue")Queue orderDeadQueue){
        return BindingBuilder.bind(orderDeadQueue).to(orderDeadExchange).with(ORDER_DEAD_ROUTING);
    }

}
