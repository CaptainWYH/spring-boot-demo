package com.wyhcode.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.wyhcode.config.DirectMqConfig.DIRECT_QUEUE;

/**
 * @author weiyuhui
 * @date 2023/7/21 16:08
 * @description
 */
@Slf4j
@Component
public class DirectConsumer {

    @RabbitListener(queues = DIRECT_QUEUE)
    public void process(Message message, Channel channel) throws IOException {
        log.info("direct队列 接收到消息：{}  延迟时间：{}ms",new String(message.getBody()),message.getMessageProperties().getExpiration());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
