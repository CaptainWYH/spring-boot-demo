package com.wyhcode.duty.handler;

import com.wyhcode.duty.LeaveNode;

/**
 * @author weiyuhui
 * @date 2023/7/12 16:41
 * @description
 */


public abstract class Leader {
    /**
     * 姓名
     */
    public String name;

    /**
     * 后继者
     */
    protected Leader successor;

    public Leader(String name){
        this.name = name;
    }

    public void setSuccessor(Leader successor){
        this.successor = successor;
    }

    public abstract void handleRequest(LeaveNode leaveNode);
}
