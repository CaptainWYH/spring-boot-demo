package com.wyhcode.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weiyuhui
 * @date 2023/6/13 14:34
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;
}
