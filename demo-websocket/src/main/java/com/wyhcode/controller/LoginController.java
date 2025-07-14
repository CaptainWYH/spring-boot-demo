package com.wyhcode.controller;

import com.wyhcode.websocket.OrderStatusEndPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @author weiyuhui
 * @Date: 2025/6/5 00:24
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    OrderStatusEndPoint orderStatusEndPoint;

    @GetMapping("/login/{id}")
    public String login(@PathVariable("id") Integer id, HttpSession session) {
        session.setAttribute("username", "zhangsan");
        session.setAttribute("id", id);
        return "success";
    }

    @GetMapping("/webhook/{orderId}")
    public String webhook(@PathVariable("orderId") Integer orderId){
        orderStatusEndPoint.onMessage("订单状态被改变了" + orderId);
        return "success";
    }
}
