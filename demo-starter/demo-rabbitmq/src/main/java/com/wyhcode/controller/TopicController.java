package com.wyhcode.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.wyhcode.bean.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

import static com.wyhcode.config.TopicMqConfig.TOPIC_EXCHANGE;

/**
 * @author weiyuhui
 * @date 2023/7/21 14:53
 * @description
 */

@RestController
@Api("主题消息")
@ConditionalOnBean(RabbitTemplate.class)
public class TopicController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendTopicMessage")
    @ApiOperation("发送主题消息")
    public String sendTopicMessage(@RequestParam("topic")String topic){
        Message message = new Message();
        message.setMessageId(String.valueOf(UUID.randomUUID()));
        message.setMessageData("消费主题：" + topic);
        message.setCreateTime(DateUtil.format(DateUtil.date(),"yyyy年-MM月-dd日 HH:mm:ss"));
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,topic, JSON.toJSONString(message));
        return "ok";
    }
}
