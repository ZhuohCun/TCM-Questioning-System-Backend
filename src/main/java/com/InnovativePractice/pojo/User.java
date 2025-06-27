package com.InnovativePractice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
    @NotNull
    private Long userId;
    private String userName;
    @JsonIgnore
    private String password;
    private String phoneNumber;
    private String gender;

}
