package com.wyhcode;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author weiyuhui
 * @date 2023/6/21 11:09
 * @description
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws UnknownHostException, ModbusIOException {
        TcpParameters tcpParameters = new TcpParameters();
        //设置tcp参数设置ip地址
        tcpParameters.setHost(InetAddress.getByName("127.0.0.1"));
        //设置TCP长连接
        tcpParameters.setKeepAlive(true);
        //TCP设置端口，这里设置默认端口502
        tcpParameters.setPort(502);
        //创建一个主机
        ModbusMaster modbusMasterTCP = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
        Modbus.setAutoIncrementTransactionId(true);
        modbusMasterTCP.connect();
        log.info("modbus connect success");

    }
}
