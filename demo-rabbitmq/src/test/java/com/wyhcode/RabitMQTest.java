package com.wyhcode;

import com.wyhcode.producer.OrderProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author weiyuhui
 * @date 2023/7/17 17:38
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabitMQTest {

    @Resource
    private OrderProducer orderProducer;

    @Test
    public void test01(){
        for (int i = 1;i <= 10; i++){
            orderProducer.createOrder(i);
        }
    }
}
