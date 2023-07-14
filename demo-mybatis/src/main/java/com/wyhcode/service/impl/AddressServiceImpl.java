package com.wyhcode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyhcode.mapper.AddressMapper;
import com.wyhcode.pojo.Address;
import com.wyhcode.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public PageInfo<Address> getAddressPage(@RequestParam("page") int page,@RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        List<Address> list = addressMapper.getAddressPage();
        PageInfo<Address> addressPageInfo = new PageInfo<>(list);
        return addressPageInfo;
    }
}
