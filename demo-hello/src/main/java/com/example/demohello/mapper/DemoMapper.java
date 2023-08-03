package com.example.demohello.mapper;

import com.example.demohello.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class DemoMapper {

    public Person getPerson(){
        Person person = new Person();
        person.setName("魏渝辉");
        person.setSex("男");
        person.setLocation("浙江宁波");
        person.setSalary(1000);
        // 手动模拟异常情况
//        int i = 1 / 0;
        return person;
    }
}
