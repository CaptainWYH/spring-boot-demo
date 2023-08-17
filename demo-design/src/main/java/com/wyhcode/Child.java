package com.wyhcode;

/**
 * @author weiyuhui
 * @date 2023/8/10 14:11
 * @description
 */

public class Child extends Person{

    public int a = 2;

    public Child(){
        super();
    }

    @Override
    public void fun() {
        System.out.println("Child fun");
        System.out.println(a);
    }
}
