package com.InnovativePractice.service;

import java.util.List;
import java.util.Map;

public interface IMeridiansServidce {
    List<Map<String, Object>> getList();

    List<Map<String, Object>> MeridianInfo(Integer meridianId);
    List<Map<String, Object>> MeridianInfo(String meridianId);

}
