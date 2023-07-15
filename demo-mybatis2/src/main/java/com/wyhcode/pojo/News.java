package com.wyhcode.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class News {

    private Long id;

    private Date addTime;

    private String introduction;

    private String picture;

    private String content;


}
