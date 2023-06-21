package com.wyhcode.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author weiyuhui
 * @date 2023/6/20 17:06
 * @description
 */
@AllArgsConstructor
@Getter
public enum ResourceTypeEnum {

    BUTTON(2),
    URI(1);

    private final Integer type;

}
