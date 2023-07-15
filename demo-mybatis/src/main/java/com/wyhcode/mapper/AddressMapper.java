package com.wyhcode.mapper;

import com.wyhcode.pojo.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper {


    List<Address> getAddressPage();

}
