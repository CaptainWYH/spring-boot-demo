package com.wyhcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyhcode.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author weiyuhui
 * @date 2023/6/13 17:40
 * @description
 */

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
