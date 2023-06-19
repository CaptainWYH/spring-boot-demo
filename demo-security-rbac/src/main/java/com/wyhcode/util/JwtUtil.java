package com.wyhcode.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.wyhcode.config.JwtConfig;
import com.wyhcode.entity.vo.user.UserPrincipal;
import com.wyhcode.enums.Consts;
import com.wyhcode.enums.StatusEnum;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.wyhcode.util.ServiceExceptionUtil.exception;

/**
 * @author weiyuhui
 * @date 2023/6/14 11:03
 * @description
 */

@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 创建JWT
     *
     * @param rememberMe  记住我
     * @param id          用户id
     * @param subject     用户名
     * @param roles       用户角色
     * @param authorities 用户权限
     * @return JWT
     */
    public String createJWT(Boolean rememberMe, Long id, String subject, List<String> roles, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder().setId(id.toString()).setSubject(subject).setIssuedAt(now).signWith(SignatureAlgorithm.HS256, jwtConfig.getKey()).claim("roles", roles).claim("authorities", authorities);

        // 设置过期时间
        Long ttl = rememberMe ? jwtConfig.getRemember() : jwtConfig.getTtl();
        if (ttl > 0) {
            builder.setExpiration(DateUtil.offsetMillisecond(now, ttl.intValue()));
        }

        String jwt = builder.compact();
        // 将生成的JWT保存至Redis
        redisTemplate.opsForValue().set(Consts.REDIS_JWT_KEY_PREFIX + subject, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }

    /**
     * 创建JWT
     *
     * @param authentication 用户认证信息
     * @param rememberMe     记住我
     * @return JWT
     */
    public String createJWT(Authentication authentication, Boolean rememberMe) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return createJWT(rememberMe, userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getRoles(), userPrincipal.getAuthorities());
    }

    /**
     * 从 request 的 header中获取JWT
     * @param request 请求
     * @return JWT
     */
    public String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StrUtil.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * 解析 JWT
     * @param jwt JWT
     * @return Claims
     */
    public Claims parseJWT(String jwt){

        try {
            Claims claims = Jwts.parser().setSigningKey(jwtConfig.getKey()).parseClaimsJws(jwt).getBody();

            //获取用户名
            String username = claims.getSubject();
            //redis key
            String redisKey = Consts.REDIS_JWT_KEY_PREFIX + username;

            //校验redis key是否存在
            Long expire = redisTemplate.getExpire(redisKey);

            //过期判断
            if (ObjectUtil.isNull(expire) || expire <= 0){
                throw exception(StatusEnum.TOKEN_EXPIRED);
            }
            //校验是否与redis 中的 jwt 一致，不一致代表用户已经注销/在不同的地方登录
            String redisToken = redisTemplate.opsForValue().get(redisKey);
            if (!StrUtil.equals(jwt,redisToken)){
                throw exception(StatusEnum.TOKEN_OUT_OF_CTRL);
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw exception(StatusEnum.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            e.printStackTrace();
            throw exception(StatusEnum.TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw exception(StatusEnum.TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            throw exception(StatusEnum.TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw exception(StatusEnum.TOKEN_PARSE_ERROR);
        }
    }

    /**
     * 从jwt中获取用户名
     * @param jwt JWT
     * @return 用户名
     */
    public String getUserNameFromJWT(String jwt){
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }
}
