package com.wyhcode.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @date 2023/7/14 14:31
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer id;

    private Integer status;

}
