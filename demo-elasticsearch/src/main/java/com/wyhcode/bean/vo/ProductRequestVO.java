package com.wyhcode.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weiyuhui
 * @date 2023/7/27 15:28
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("产品查询Model")
public class ProductRequestVO {

    @ApiModelProperty("页码")
    private Integer curPage;

    @ApiModelProperty("数量")
    private Integer pageSize;

    @ApiModelProperty("关键词")
    private String keyword;
}
