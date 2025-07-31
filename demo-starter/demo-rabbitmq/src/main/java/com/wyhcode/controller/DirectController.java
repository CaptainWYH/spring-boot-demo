package com.wyhcode.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.wyhcode.config.DirectMqConfig.DIRECT_EXCHANGE;
import static com.wyhcode.config.DirectMqConfig.DIRECT_ROUTING_KEY;

/**
 * @author weiyuhui
 * @date 2023/7/21 16:02
 * @description
 */

@Slf4j
@RestController
@Api("Direct Controller")
@ConditionalOnBean(RabbitTemplate.class)
public class DirectController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct/sendMsgTTL")
    public String sendMsg(@RequestParam("ttl") int ttl,@RequestParam("msg")String msg){
        log.info("发送了一条消息：{}，延迟时间：{}s",msg,ttl);
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE,DIRECT_ROUTING_KEY,msg,e->{
            // 设置消息延迟时间
            e.getMessageProperties().setExpiration(String.valueOf(ttl * 1000));
            e.getMessageProperties().setDelay(ttl * 1000);
            return e;
        });
        return "ok";
    }
}
