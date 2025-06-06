package com.InnovativePractice.service;

import com.InnovativePractice.pojo.RespondMessage;
import com.InnovativePractice.pojo.User;
import com.InnovativePractice.pojo.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface IUserService {
    //根据用户名返回用户信息
    public User getUserByUsername(String username);
    public void register(String username, String password);
    void update(User user);


    String chat(int userId,String question) throws IOException;

    List getMedicalCase(int dialogueId);

    String reset(int userID);
}
