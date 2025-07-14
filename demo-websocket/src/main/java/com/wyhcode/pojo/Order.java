package com.wyhcode.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @Date: 2025/6/5 00:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;

    private String orderCode;

    private Integer status;
}
