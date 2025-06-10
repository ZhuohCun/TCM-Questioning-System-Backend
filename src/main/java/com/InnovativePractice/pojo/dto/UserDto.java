package com.InnovativePractice.pojo.dto;

//import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserDto {
//    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    private String phoneNumber;
    private String gender;
    @Length(min = 4, max = 16, message = "密码长度必须在6到16之间")
    @NotNull(message = "密码不能为空")
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
