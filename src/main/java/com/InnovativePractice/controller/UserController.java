package com.InnovativePractice.controller;
import com.InnovativePractice.pojo.RespondMessage;
import com.InnovativePractice.pojo.User;
import com.InnovativePractice.service.UserService;
import com.InnovativePractice.service.streamMemoryAiassistant;
import com.InnovativePractice.utils.JwtUtil;
import com.InnovativePractice.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.InnovativePractice.utils.HashUtils.getMd5String;
import static com.InnovativePractice.utils.JwtUtil.parseToken;

@RestController//接口方法返回对象，转换为json数据
@RequestMapping("/user")
@Validated

public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil=new JwtUtil();

    @PostMapping("/register")
    public RespondMessage registerUser(
            String username,
            String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            // 直接使用原始密码，让Service层处理加密
            userService.register(username, password);
            return RespondMessage.success("注册成功");
        } else {
            return RespondMessage.error("用户已存在");
        }
    }

    @PostMapping("/login")
//实现登录
    public RespondMessage login(String username,
                                String password) {
        User loginUser = userService.getUserByUsername(username);
        if (loginUser == null) {
            return RespondMessage.error("用户不存在");
        }

        // 测试MD5加密的一致性
        String test1 = getMd5String(password);
        String test2 = getMd5String(password);
        System.out.println("Test 1 MD5: " + test1);
        System.out.println("Test 2 MD5: " + test2);
        System.out.println("Are they equal? " + test1.equals(test2));

        // 原有的验证逻辑
        String inputPasswordHash = getMd5String(password);
        String storedPassword = loginUser.getPassword();
        System.out.println("Login - Input password: " + password);
        System.out.println("Login - Input password hash: " + inputPasswordHash);
        System.out.println("Login - Stored password hash: " + storedPassword);

        if (inputPasswordHash.equals(storedPassword)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            claims.put("userId", loginUser.getUserId());

            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 12, TimeUnit.HOURS);

            return RespondMessage.success(token);
        }
        return RespondMessage.error("密码错误");
    }

    @GetMapping("/UserInfo")
    public RespondMessage<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> map = parseToken(token);


        String username = (String) map.get("username");

        User user = userService.getUserByUsername(username);

        return RespondMessage.success(user);
    }

    @PutMapping("/update")
    public RespondMessage update(@RequestBody User user) {
        userService.update(user);
        return RespondMessage.success("修改成功");
    }

    @PostMapping("/newDialogue")
    public RespondMessage<String> newDialogue() {
        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (int) map.get("userId");
        return RespondMessage.success(userService.reset(userId));
    }


    @GetMapping(value = "/chatString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String chatString( String question,String sequence) throws IOException {
        System.out.println("Received SSE request with question: " + sequence);
        Map<String, Object> map = ThreadLocalUtil.get();
        int UserId=(int) map.get("userId");
        int sequenceint=Integer.parseInt(sequence);
        String res=userService.chat(UserId,question,sequenceint);
        return res;
    }

    @PostMapping("/verify")
    public RespondMessage verifyToken(@RequestHeader("Authorization") String token) {
        if (token == null) {
            return RespondMessage.success("-1");
        }
        try {
            Map<String, Object> claimsMap = JwtUtil.parseToken(token);
            if (claimsMap == null) {
                return RespondMessage.success("-1");
            }else {
                return RespondMessage.success("1");
            }
        } catch (Exception e) {
            return RespondMessage.success("-1");
        }
    }
}

