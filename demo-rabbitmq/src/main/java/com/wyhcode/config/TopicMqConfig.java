package com.wyhcode.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author weiyuhui
 * @date 2023/7/21 11:09
 * @description
 */

@Configuration
public class TopicMqConfig {

    public static final String MAN_QUEUE = "manQueue";

    public static final String WOMAN_QUEUE = "womanQueue";

    public static final String PEOPLE_QUEUE = "peopleQueue";

    public static final String MAN_ROUTING_KEY_QUEUE = "topic.man";

    public static final String WOMAN_ROUTING_KEY_QUEUE = "topic.woman";

    public static final String PEOPLE_ROUTING_KEY_QUEUE = "topic.*";

    // 交换机

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    @Bean
    public Queue manQueue(){
        return new Queue(MAN_QUEUE,true,false,false,null);
    }

    @Bean
    public Queue womanQueue(){
        return new Queue(WOMAN_QUEUE,true,false,false,null);
    }

    @Bean
    public Queue peopleQueue(){
        return new Queue(PEOPLE_QUEUE,true,false,false,null);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding peopleBindingExchange(@Qualifier("peopleQueue") Queue peopleQueue,
                                      @Qualifier("topicExchange")TopicExchange topicExchange){
        return BindingBuilder.bind(peopleQueue).to(topicExchange).with(PEOPLE_ROUTING_KEY_QUEUE);
    }

    @Bean
    public Binding manBindingExchange(@Qualifier("manQueue") Queue manQueue,
                                         @Qualifier("topicExchange")TopicExchange topicExchange){
        return BindingBuilder.bind(manQueue).to(topicExchange).with(MAN_ROUTING_KEY_QUEUE);
    }

    @Bean
    public Binding womanBindingExchange(@Qualifier("womanQueue") Queue womanQueue,
                                         @Qualifier("topicExchange")TopicExchange topicExchange){
        return BindingBuilder.bind(womanQueue).to(topicExchange).with(WOMAN_ROUTING_KEY_QUEUE);
    }
}
