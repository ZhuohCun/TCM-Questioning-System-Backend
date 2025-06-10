package com.InnovativePractice;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.InnovativePractice.mapper")
@EnableAsync

public class InnovativePracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(InnovativePracticeApplication.class, args);
    }
}
