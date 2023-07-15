package com.wyhcode.controller;


import com.github.pagehelper.PageInfo;
import com.wyhcode.pojo.Address;
import com.wyhcode.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/address")
@Api(tags = "地址控制层")
public class AddressController {

    @Resource
    private AddressService addressService;
    @GetMapping("/getPage")
    @ApiOperation(value = "查询分页")
    public PageInfo<Address> getPage(int page, int size){
        return addressService.getAddressPage(page,size);
    }

}
