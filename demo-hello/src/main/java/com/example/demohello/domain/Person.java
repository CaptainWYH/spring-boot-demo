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

    private String name;

    private Integer salary;


    private String sex;

    private String location;
}
