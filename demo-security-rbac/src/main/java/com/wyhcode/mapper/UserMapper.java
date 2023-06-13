package com.wyhcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyhcode.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author weiyuhui
 * @date 2023/6/13 17:36
 * @description
 */

@Mapper
public interface UserMapper extends BaseMapper<User>{

}
