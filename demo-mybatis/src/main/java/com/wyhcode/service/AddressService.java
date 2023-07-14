package com.wyhcode.service;

import com.github.pagehelper.PageInfo;
import com.wyhcode.pojo.Address;

import java.util.List;

public interface AddressService {
    PageInfo<Address> getAddressPage(int page, int size);
}
