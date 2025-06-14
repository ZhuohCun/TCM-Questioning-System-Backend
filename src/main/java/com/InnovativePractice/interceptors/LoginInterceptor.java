package com.InnovativePractice.interceptors;

import com.InnovativePractice.utils.JwtUtil;
import com.InnovativePractice.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler) throws Exception{
        //令牌验证
        //通过resquest对象得到authorization头
        String token = request.getHeader("Authorization");
        try {
            ValueOperations<String,String> operations=stringRedisTemplate.opsForValue();
            String rediStoken= operations.get(token);
            if(rediStoken==null)
            {
                throw new RuntimeException();
            }
            Map<String,Object> claims = JwtUtil.parseToken(token);

            //把业务数据存到threadlocal里
            ThreadLocalUtil.set(claims);
            return true;

        }catch (Exception e)
            {
            response.setStatus(401);
            return false;
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal的数据
        ThreadLocalUtil.remove();

    }
}
