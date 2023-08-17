package com.wyhcode;

/**
 * @author weiyuhui
 * @date 2023/8/10 14:10
 * @description
 */

public class Person {

    public int a = 1;

    public Person(){
        super();
        System.out.println(a);
        this.fun();
    }

    public void fun(){
        System.out.println("Person fun");
    }
}
