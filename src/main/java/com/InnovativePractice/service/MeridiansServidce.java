package com.InnovativePractice.service;

import com.InnovativePractice.mapper.MeridiansMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MeridiansServidce{
    @Autowired
    private MeridiansMapper meridiansMapper;
    public List<Map<String, Object>> getList() {
        List<Map<String, Object>> list=meridiansMapper.getMeridiansList();
        return list;
    }

    public List<Map<String, Object>> MeridianInfo(Integer meridianId) {
        List<Map<String, Object>> list=meridiansMapper.getMeridiansInfo(meridianId);
        return list;
    }

    public List<Map<String, Object>> MeridianInfo(String meridianName) {
        List<Map<String, Object>> list=meridiansMapper.getMeridiansInfoByname(meridianName);
        return list;
    }
}
