package com.wyhcode.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("地址")
public class Address {

    private Long id;

    private Date addTime;

    private Long userId;

    private String address;

    private String name;

    private String phone;

    private String isDefault;
}
