package com.InnovativePractice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper


public interface MeridiansMapper {
    @Select("select acupoint.id,acupoint.name,meridian.meridian_name from acupoint,meridian_acupoint,meridian where acupoint.id=meridian_acupoint.acupoint_id and meridian_acupoint.meridian_id=meridian.id")
    List<Map<String, Object>> getMeridiansList();

    @Select("select acupoint.id,acupoint.name,acupoint.location_description,acupoint.technique,meridian_acupoint.meridian_id from acupoint,meridian_acupoint where id=#{merdian_id} and acupoint.id=meridian_acupoint.acupoint_id")
    List<Map<String, Object>> getMeridiansInfo(Integer merdian_id);
    @Select("select acupoint.id,acupoint.name,acupoint.location_description,acupoint.technique,meridian_acupoint.meridian_id from acupoint,meridian_acupoint where name=#{merdian_id} and acupoint.id=meridian_acupoint.acupoint_id")
    List<Map<String, Object>> getMeridiansInfoByname(String meridianName);
}
