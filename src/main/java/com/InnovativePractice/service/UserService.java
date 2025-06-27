package com.InnovativePractice.service;
import com.InnovativePractice.mapper.HistoricalDialogueMapper;
import com.InnovativePractice.mapper.UserMapper;
import com.InnovativePractice.pojo.User;
import com.InnovativePractice.utils.Neo4jQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.InnovativePractice.utils.HashUtils.getMd5String;
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private streamMemoryAiassistant StreamMemoryAiassistant;


    @Autowired
    private HistoricalDialogueMapper historyDialogueMapper;

    public UserService(){

    }
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void register(String username, String password) {
        String encryptedPassword = getMd5String(password);
        userMapper.insert(username, encryptedPassword);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public String chat(int userId, String question,int sequence) throws IOException {
        //找到当前 用户的 轮次id
        Integer sequenceId = sequence;
        if(sequenceId==null){
            historyDialogueMapper.saveDialogue(userId,1,null,null);
            sequenceId=1;
        }else if(sequenceId==-1){
            if(historyDialogueMapper.getSequenceIdByUserId(userId)==null){
                historyDialogueMapper.saveDialogue(userId,1,null,null);
                sequenceId=1;
            }else {
                sequenceId=historyDialogueMapper.getSequenceIdByUserId(userId);
            }
        }
        String response=StreamMemoryAiassistant.chat(question);
        //保存对话
        historyDialogueMapper.saveDialogue(userId,sequenceId,response,question);
        //返回响应
        return response;
    }
    public String reset(int userId) {
        int sequenceId = 0;
        if(historyDialogueMapper.getSequenceIdByUserId(userId)==null){
        }else {
            sequenceId=historyDialogueMapper.getSequenceIdByUserId(userId);
        }
        if(historyDialogueMapper.hasRestarted(userId,sequenceId)==null)
        {
            return "已经重置对话";
        }
        else {
            historyDialogueMapper.saveDialogue(userId,sequenceId+1,null,null);
            return "重置对话成功！";
        }
    }

}
