package com.InnovativePractice.service;
import com.InnovativePractice.mapper.HistoricalDialogueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class HistoricalDialogueService{
    @Autowired
    private HistoricalDialogueMapper historicalDialogueMapper;
    public List<Map<String, Object>> getList(int userId) {
        List<Map<String, Object>> list=historicalDialogueMapper.getHistoryList(userId,0,99);
        return list;
    }

    public List<Map<String, Object>> getHistry(int userId, Integer sequenceId) {
        List<Map<String, Object>> list=historicalDialogueMapper.getHistory(userId,sequenceId);
        return list;
    }

}
