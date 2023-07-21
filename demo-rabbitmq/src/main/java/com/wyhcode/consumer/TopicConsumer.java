package com.wyhcode.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.wyhcode.config.TopicMqConfig.*;

/**
 * @author weiyuhui
 * @date 2023/7/21 15:15
 * @description 主题消费者
 */

@Slf4j
@Component
public class TopicConsumer {


    @RabbitListener(queues = WOMAN_QUEUE)
    public void womanListener(Message message, Channel channel) throws IOException {
        log.info("woman_topic 消费者，接收到消息：{}", JSONObject.parseObject(new String(message.getBody()),com.wyhcode.bean.Message.class));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = MAN_QUEUE)
    public void manListener(Message message, Channel channel) throws IOException {
        log.info("man_topic 消费者，接收到消息：{}",JSONObject.parseObject(new String(message.getBody()),com.wyhcode.bean.Message.class));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = PEOPLE_QUEUE)
    public void peopleListener(Message message, Channel channel) throws IOException {
        log.info("people_topic 消费者，接收到消息：{}",JSONObject.parseObject(new String(message.getBody()),com.wyhcode.bean.Message.class));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
