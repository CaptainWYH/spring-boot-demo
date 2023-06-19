package com.wyhcode.entity.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author weiyuhui
 * @date 2023/6/16 17:25
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginUserResponseVO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆token
     */
    private String token;
}
