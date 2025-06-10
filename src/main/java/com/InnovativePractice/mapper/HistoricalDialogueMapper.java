package com.InnovativePractice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface HistoricalDialogueMapper {
    @Insert("insert into historical_dialogue_info(date,question, responds,sequence_id,user_id) values (now(),#{question},#{response},#{sequenceId},#{userId})")
    void saveDialogue(int userId, int sequenceId, String response, String question) ;
    @Select("select sequence_id from historical_dialogue_info where user_id=#{userId} order by sequence_id DESC limit 1")
    Integer  getSequenceIdByUserId(int userId);
    @Select("""
    SELECT question, sequence_id,date
    FROM (
        SELECT question, sequence_id, date,
               ROW_NUMBER() OVER (PARTITION BY sequence_id ORDER BY date ASC) AS rn  
        FROM historical_dialogue_info  
        WHERE user_id = #{userId}  
    ) AS subquery  
    WHERE rn = 2  
    LIMIT #{offset}, #{count}  
""")
    List<Map<String, Object>> getHistoryList(Integer userId, Integer offset, Integer count);
    @Select("select historical_dialogue_info.question,historical_dialogue_info.responds,historical_dialogue_info.date,historical_dialogue_info.sequence_id from historical_dialogue_info where sequence_id=#{sequenceId} and user_id=#{userId} and question!= \"\" and responds!=\"\"")
    List<Map<String, Object>> getHistory(int userId, Integer sequenceId);

    @Select("""
    SELECT count(*)
    FROM (
        SELECT question, sequence_id, date,
               ROW_NUMBER() OVER (PARTITION BY sequence_id ORDER BY date ASC) AS rn  
        FROM historical_dialogue_info  
        WHERE user_id = #{userId}  
    ) AS subquery  
    WHERE rn = 2  
    ORDER BY date ASC  
    LIMIT #{offset}, #{count}  
""")
    List<Map<String, Object>> getHistoryListLength(Integer userId, Integer offset, Integer count);


    @Select("select historical_dialogue_info.question from historical_dialogue_info where user_id=#{userId} and sequence_id =#{sequenceId} order by date desc limit 1")
     String hasRestarted(int userId, int sequenceId);

}