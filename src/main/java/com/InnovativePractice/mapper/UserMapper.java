package com.InnovativePractice.mapper;

import com.InnovativePractice.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户

    @Select("SELECT user_id,username,password,phone_number,gender FROM users WHERE username = #{username}")
    User getUserByUsername(String username);
    // 注册用户

    @Insert("INSERT INTO users (username, password) VALUES (#{username}, #{password})")
    void insert(@Param("username") String username, @Param("password") String password);
    @Update("update users set gender=#{gender} ,password=#{password},phone_number=#{phoneNumber} where user_id=#{userId}")
    void update(User user);
}