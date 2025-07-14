package com.example.demohello.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.example.demohello.annocation.LogPoint;
import com.example.demohello.domain.Person;
import com.example.demohello.mapper.DemoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    private DemoMapper demoMapper;

    @GetMapping("/get")
//    @LogPoint
    public Person getPerson() throws InterruptedException {
//        return demoMapper.getPerson();
        // 阻塞50ms，理论单线程可达到25QPS，但是考虑到有网络关系，可能实际在20左右
        Thread.sleep(40);
        return new Person();
    }

    @GetMapping("getCaptchaImg")
    public String getCaptchaImg() {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 10);
        return captcha.getImageBase64();
    }

}
