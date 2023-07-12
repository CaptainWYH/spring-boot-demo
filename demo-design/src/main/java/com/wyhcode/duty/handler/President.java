package com.wyhcode.duty.handler;

import com.wyhcode.duty.LeaveNode;
import com.wyhcode.duty.handler.Leader;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:35
 * @description
 */

public class President extends Leader {
    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if(leaveNode.getNumber() <= 15){   //小于15天校长长审批
            System.out.println("校长" + name + "审批" +leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        }
        else{     //否则不允批准
            System.out.println("请假天天超过15天,不批准...");
        }
    }

    public President(String name) {
        super(name);
    }
}
