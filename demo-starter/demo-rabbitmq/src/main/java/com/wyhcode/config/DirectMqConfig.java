package com.wyhcode.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author weiyuhui
 * @date 2023/7/21 15:56
 * @description
 */

@Configuration
public class DirectMqConfig {

    public static final String DIRECT_QUEUE = "directQueue";

    public static final String DIRECT_EXCHANGE = "directExchange";

    public static final String DIRECT_ROUTING_KEY = "routing.test";


    @Bean
    public Queue directQueue(){
        return QueueBuilder.durable(DIRECT_QUEUE).build();
    }

    @Bean
    public CustomExchange directExchange(){
        HashMap<String, Object> args = new HashMap<>(1);
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DIRECT_EXCHANGE, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding directBindingExchange(@Qualifier(DIRECT_QUEUE)Queue directQueue,
                                         @Qualifier(DIRECT_EXCHANGE)CustomExchange directExchange){
        return BindingBuilder.bind(directQueue).to(directExchange).with(DIRECT_ROUTING_KEY).noargs();
    }
}
