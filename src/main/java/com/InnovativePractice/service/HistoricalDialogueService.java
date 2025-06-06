package com.InnovativePractice.service;
import com.InnovativePractice.mapper.HistoricalDialogueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class HistoricalDialogueService implements IHistoricalDialogueService {
    @Autowired
    private HistoricalDialogueMapper historicalDialogueMapper;
    @Override
    public List<Map<String, Object>> getList(int userId, int pageId, int count) {
        System.out.println("111");
        int offset=(pageId-1)*count;
        List<Map<String, Object>> list=historicalDialogueMapper.getHistoryList(userId,offset,count);
        System.out.println(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> getHistry(int userId, Integer sequenceId) {
        List<Map<String, Object>> list=historicalDialogueMapper.getHistory(userId,sequenceId);
        return list;
    }
}
