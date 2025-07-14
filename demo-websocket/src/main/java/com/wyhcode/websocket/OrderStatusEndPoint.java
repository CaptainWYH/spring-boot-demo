package com.wyhcode.websocket;

import cn.hutool.json.JSONUtil;
import com.wyhcode.config.GetHttpSessionConfigurator;
import com.wyhcode.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 订单状态Websocket
 * @author weiyuhui
 * @Date: 2025/6/4 23:50
 **/
@ServerEndpoint(value = "/order-stats",configurator = GetHttpSessionConfigurator.class)
@Component
@Slf4j
public class OrderStatusEndPoint {

    private static final Map<String,Session> SESSION_HOLDER = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("websocket 连接上来了，session{}", session);
        // 1、将session进行保存
        SESSION_HOLDER.put(session.getId(),session);
        // 2、广播消息
    }

    @OnClose
    public void onClose(Session session) {
        log.info("sessionid ：{} 断开连接", session.getId());
        SESSION_HOLDER.remove(session.getId());
    }

    @OnMessage
    public void onMessage(String message) {
//        try {
////            SESSION_HOLDER.get().getBasicRemote().sendText(JSONUtil.toJsonStr(new Order(1,"1",1)));
//        } catch (IOException e) {
//            log.error("服务器推送订单数据异常");
//            throw new RuntimeException(e);
//        }
    }
}
