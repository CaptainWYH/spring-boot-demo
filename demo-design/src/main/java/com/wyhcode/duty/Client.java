package com.wyhcode.duty;

import com.wyhcode.duty.handler.Dean;
import com.wyhcode.duty.handler.DepartmentHead;
import com.wyhcode.duty.handler.Instructor;
import com.wyhcode.duty.handler.President;

/**
 * @author weiyuhui
 * @date 2023/7/12 17:36
 * @description 责任链模式
 */

public class Client {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("魏渝辉"); // 辅导员
        DepartmentHead departmentHead = new DepartmentHead("小莉"); //系主任
        Dean dean = new Dean("王强"); // 院长
        President president = new President("王元"); // 校长

        instructor.setSuccessor(departmentHead);
        departmentHead.setSuccessor(dean);
        dean.setSuccessor(president);

        // 请假3天
        LeaveNode leaveNode = new LeaveNode(3, "魏渝辉");
        instructor.handleRequest(leaveNode);
        LeaveNode leaveNode1 = new LeaveNode(9, "魏渝辉");
        instructor.handleRequest(leaveNode1);

        LeaveNode leaveNode2 = new LeaveNode(15, "魏渝辉");
        instructor.handleRequest(leaveNode2);

        LeaveNode leaveNode4 = new LeaveNode(17, "魏渝辉");
        instructor.handleRequest(leaveNode4);

    }
}
