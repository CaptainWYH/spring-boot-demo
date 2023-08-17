package com.wyhcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author weiyuhui
 * @date 2023/7/12 16:30
 * @description
 */

public class Main {
    public static void main(String[] args) {
        String s = "null,生成vlog出错!,生成vlog出错!,生成vlog出错!,生成vlog出错!";
        String s1 = distinctDescription(s);
        System.out.println(s1);
        Child child = new Child();
    }
    public static String distinctDescription(String description){
        String[] split = description.split(",");
        Set<String> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : split) {
            if (!s.equals("null")){
                set.add(s);
            }
        }
        return String.join(",",set);
    }
}
