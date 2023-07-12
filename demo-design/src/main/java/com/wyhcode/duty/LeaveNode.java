package com.wyhcode.duty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @date 2023/7/12 16:35
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveNode {

    /**
     * 请假天数
     */
    private int number;

    /**
     * 请假人
     */
    private String person;
}
