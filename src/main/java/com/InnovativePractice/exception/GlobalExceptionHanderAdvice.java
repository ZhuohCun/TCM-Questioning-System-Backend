package com.InnovativePractice.exception;

import com.InnovativePractice.pojo.RespondMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHanderAdvice {
    //TODO: implement global exception handling
    @ExceptionHandler({Exception.class})
    public RespondMessage handleException(Exception e) {
        //TODO: handle exception and return a RespondMessage object
        e.printStackTrace();
        return  RespondMessage.error(StringUtils.hasLength(e.getMessage())?e.getMessage(): "操作失败");

    }
}
