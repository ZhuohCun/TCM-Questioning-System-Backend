package com.InnovativePractice.filter;

import com.InnovativePractice.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * JWT认证过滤器
 * 用于处理基于JWT的身份认证
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final StringRedisTemplate redisTemplate;

    /**
     * 构造函数，注入Redis模板
     * @param redisTemplate Redis操作模板
     */
    public JwtAuthenticationFilter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 过滤器的核心处理方法
     * @param request HTTP请求
     * @param response HTTP响应
     * @param filterChain 过滤器链
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 从请求头中获取JWT token
        String token = request.getHeader("Authorization");
        
        // 对登录和注册请求直接放行，不需要验证token
        if (request.getRequestURI().contains("/user/login") || 
            request.getRequestURI().contains("/user/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 验证token的有效性
        if (token != null && redisTemplate.hasKey(token)) {
            try {
                // 解析JWT token
                Map<String, Object> claims = JwtUtil.parseToken(token);
                String username = (String) claims.get("username");
                
                // 创建认证对象，这里简单处理，没有添加权限信息
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                
                // 将认证信息设置到Spring Security的上下文中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // token解析失败，返回401未授权状态码
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            // token不存在或已过期，返回401未授权状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 继续执行过滤器链
        filterChain.doFilter(request, response);
    }
} 