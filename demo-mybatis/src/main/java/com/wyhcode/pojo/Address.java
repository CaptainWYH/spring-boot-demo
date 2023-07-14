package com.wyhcode.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private Long id;

    private Date addTime;

    private Long userId;

    private String address;

    private String name;

    private String phone;

    private String isDefault;
}
