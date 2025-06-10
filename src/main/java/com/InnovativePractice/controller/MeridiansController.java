package com.InnovativePractice.controller;

import com.InnovativePractice.pojo.RespondMessage;
import com.InnovativePractice.service.MeridiansServidce;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // 接口方法返回对象，转换为json数据
@RequestMapping("/Meridians")
@Validated
public class MeridiansController {
    @Autowired
    MeridiansServidce meridiansServidce;
    @GetMapping("/GetMeridianList")
    public RespondMessage getMeridianList(){
        List<Map<String, Object>> list = meridiansServidce.getList();
        return RespondMessage.success(list);
    }
    @GetMapping("/GetMeridianInfo")
    public RespondMessage getMeridianInfo(@RequestParam @Min(1) Integer MeridianId)
    {
        List<Map<String, Object>> list = meridiansServidce.MeridianInfo(MeridianId);
        return RespondMessage.success(list);

    }
    @GetMapping("/GetMeridianInfoByName")
    public RespondMessage getMeridianInfoByname(@RequestParam  String MeridianName)
    {
        List<Map<String, Object>> list = meridiansServidce.MeridianInfo(MeridianName);
        return RespondMessage.success(list);

    }
}