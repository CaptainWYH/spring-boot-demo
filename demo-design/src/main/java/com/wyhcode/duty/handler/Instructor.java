package com.wyhcode.duty.handler;

import com.wyhcode.duty.LeaveNode;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:28
 * @description
 */

public class Instructor extends Leader{
    public Instructor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if(leaveNode.getNumber()<= 3){   //小于3天辅导员审批
            System.out.println("辅导员" + name + "审批" +leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        }
        else{     //否则传递给系主任
            if(this.successor != null){
                this.successor.handleRequest(leaveNode);
            }
        }
    }

}
