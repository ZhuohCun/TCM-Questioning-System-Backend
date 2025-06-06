package com.InnovativePractice.service;



import java.util.List;
import java.util.Map;

public interface IHistoricalDialogueService {
    List<Map<String, Object>> getList(int userId, int pageId, int count);

    List<Map<String, Object>> getHistry(int userId, Integer sequenceId);
}
