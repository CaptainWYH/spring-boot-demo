package com.wyhcode.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    /**
     * 用户名或邮箱或手机号
     */
    @NotNull(message = "用户名不能为空")
    private String usernameOrEmailOrPhone;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

}
