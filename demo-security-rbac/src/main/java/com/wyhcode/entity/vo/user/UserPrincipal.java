package com.wyhcode.entity.vo.user;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wyhcode.entity.Permission;
import com.wyhcode.entity.Role;
import com.wyhcode.entity.User;
import com.wyhcode.enums.Consts;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 自定义User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserPrincipal implements UserDetails {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    @JsonIgnore
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 性别，男-1，女-2
     */
    private Integer sex;

    /**
     * 状态，启用-1，禁用-0
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 用户角色列表
     */
    private List<String> roles;

    /**
     * 用户权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetails create(User user, List<Role> roles, List<Permission> permissions) {
        //用户角色名称抽取
        List<String> roleNames = roles.stream().map(Role::getName).collect(Collectors.toList());
        //用户权限
        List<SimpleGrantedAuthority> authorities = permissions.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getPermission()))
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getNickname(), user.getPhone(), user.getEmail(), user.getBirthday(), user.getSex(), user.getStatus(), user.getCreateTime(), user.getUpdateTime(), roleNames, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.status, Consts.ENABLE);
    }
}
