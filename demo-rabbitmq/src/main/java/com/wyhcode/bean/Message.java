package com.wyhcode.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @date 2023/7/21 14:59
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String messageId;

    private String messageData;

    private String createTime;

}
