package com.wyhcode.pojo.vo;

import com.wyhcode.pojo.Address;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("新闻 responseVO")
public class NewsResponseVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("时间")
    private Date addTime;

    @ApiModelProperty("介绍")
    private String introduction;

    @ApiModelProperty("图片地址")
    private String picture;

    @ApiModelProperty("内容")
    private String content;

}
