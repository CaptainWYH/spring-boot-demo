package com.example.demohello.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @date 2023/7/10 10:49
 * @description
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    /**
     * 姓名
     */
    private String name;

    /**
     * 收入
     */
    private Integer salary;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地址
     */
    private String location;
}
