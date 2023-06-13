package com.wyhcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyhcode.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author weiyuhui
 * @date 2023/6/13 17:39
 * @description
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select(value = "SELECT sec_role.* FROM sec_role,sec_user,sec_user_role WHERE sec_user.id = sec_user_role.user_id AND sec_role.id = sec_user_role.role_id AND sec_user.id = #{userId}")
    List<Role> selectByUserId(@Param("userId") Long userId);
}
