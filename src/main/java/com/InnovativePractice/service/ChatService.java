package com.InnovativePractice.service;

import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService {
    public  String Chat(String question) {
        return "你好我是您的个人针灸医生，请问有什么可以帮到您！";
    }
}
