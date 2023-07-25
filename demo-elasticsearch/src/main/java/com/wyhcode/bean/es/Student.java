package com.wyhcode.bean.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author weiyuhui
 * @date 2023/7/25 16:58
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "student")
public class Student {

    @Id
    @Field(store = true,type = FieldType.Keyword)
    private String sId;

    @Field(store = true,type = FieldType.Keyword)
    private String sName;

    @Field(store = true,type = FieldType.Text)
    private String sAddress;

    @Field(store = true,type = FieldType.Integer)
    private Integer sAge;

    @Field(store = true,type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date sCreateTime;

    @Field(type = FieldType.Keyword)
    private String[] sCourseList;

}
