package com.InnovativePractice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Neo4jQueryUtil {

    private final Neo4jClient neo4jClient;

    @Autowired
    public Neo4jQueryUtil(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    /**
     * 执行查询并返回结果的JSON字符串
     *
     * @param query Cypher查询语句
     * @return 查询结果的JSON字符串
     */
    public String executeQuery(String query) {
        // 执行查询并返回 List<Map<String, Object>> 类型的结果
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) neo4jClient.query(query).fetch().all();

        // 将查询结果转换为JSON字符串
        return toJson(resultList);
    }

    /**
     * 使用Jackson将对象转换为JSON字符串
     *
     * @param object 要转换的对象
     * @return JSON字符串
     */
//    private String toJson(Object object) {
//        try {
//            // 使用Jackson的ObjectMapper将结果对象转换为JSON字符串
//            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
//            return objectMapper.writeValueAsString(object);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to convert result to JSON", e);
//        }
//    }
    private String toJson(Object object) {
        try {
            // 使用Jackson的ObjectMapper将结果对象转换为格式化的JSON字符串
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            // 使用writerWithDefaultPrettyPrinter()使JSON格式化，添加换行符和缩进
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert result to JSON", e);
        }
    }

}
