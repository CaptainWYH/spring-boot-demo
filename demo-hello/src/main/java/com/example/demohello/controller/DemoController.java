package com.example.demohello.controller;


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
    @LogPoint
    public Person getPerson(){
        return demoMapper.getPerson();
    }
}
