package com.InnovativePractice.controller;
import com.InnovativePractice.pojo.RespondMessage;
import com.InnovativePractice.service.HistoricalDialogueService;
import com.InnovativePractice.utils.JwtUtil;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController // 接口方法返回对象，转换为json数据
@RequestMapping("/HistoricalDialogueInfo")
@Validated
public class HistoricalDialogueController {
    @Autowired
    private HistoricalDialogueService historicalDialogueService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/getList")
    public RespondMessage getList(@RequestHeader(name = "Authorization") String token)
    {
        Map<String, Object> map = JwtUtil.parseToken(token);
        if (map == null) {
            return RespondMessage.error("Invalid token");
        }
        int userId = (int) map.get("userId");
        List<Map<String, Object>> list = historicalDialogueService.getList(userId);
        return RespondMessage.success(list);
    }
    @GetMapping("/getHistroy")
    public RespondMessage gethistory(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam @Min(1) Integer sequenceId)
    {
        Map<String, Object> map = JwtUtil.parseToken(token);
        if (map == null) {
            return RespondMessage.error("Invalid token");
        }
        int userId = (int) map.get("userId");
        List<Map<String, Object>> list = historicalDialogueService.getHistry(userId, sequenceId);
        return RespondMessage.success(list);
    }
}