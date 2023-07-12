package com.wyhcode.duty.handler;

import com.wyhcode.duty.LeaveNode;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:34
 * @description
 */

public class Dean extends Leader{
    public Dean(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if(leaveNode.getNumber() <= 10){   //小于10天院长审批
            System.out.println("院长" + name + "审批" +leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        }
        else{     //否则传递给校长
            if(this.successor != null){
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}
