//package com.newsql.InnovativePractice.pojo;
//
//import org.springframework.http.HttpStatus;
//
//public class RespondMessage<T> {
//    private Integer code;//状态码 200 成功 404 失败 500 服务器错误
//    private String message;//返回信息
//    private T data;//返回数据
//    public static <T>RespondMessage<T> success(T data) {
//        RespondMessage respondMessage = new RespondMessage();
//        respondMessage.setCode(HttpStatus.OK.value());//200
//        respondMessage.setMessage("success");
//        respondMessage.setData(data);
//        return respondMessage;
//    }
//
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//}
//
//
package com.InnovativePractice.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RespondMessage<T> {
    private Integer code; // 状态码 200 成功 404 失败 500 服务器错误
    private String message; // 返回信息
    private T data; // 返回数据

    // 静态方法：成功响应
    public static <T> RespondMessage<T> success(T data) {
        // 使用构造函数创建 RespondMessage 实例
        return new RespondMessage<>(HttpStatus.OK.value(), "success", data);

    }
    public static RespondMessage success(){
        return new RespondMessage(HttpStatus.OK.value(), "success", null);
    }
    public static <T> RespondMessage<T> error(String message) {
        return new RespondMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }


}