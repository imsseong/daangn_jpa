package me.seongim.daangn.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.seongim.daangn.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@Data
public class ApiResult<T> {

    @JsonProperty("c")
    private int code = 999;

    @JsonProperty("m")
    private String message;

    @JsonProperty("d")
    private T data;

    @JsonProperty("ts")
    private long timestamp = 0;

    @JsonIgnore
    private HttpStatus httpStatus;

    private ApiResult() {
        super();
    }

    public ApiResult<T> code(int code) {
        setCode(code);
        return this;
    }

    public ApiResult<T> message(String message) {
        setMessage(message);
        return this;
    }

    public static <T> ApiResult<T> ok(T data) {
        return with(data).code(0);
    }

    public static <T> ApiResult<T> ok(T data, String message) {
        return with(data).code(0).message(message);
    }

    public static <T> ApiResult<T> with(T data) {
        ApiResult<T> response = new ApiResult<>();
        response.data = data;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

    public static <T> ApiResult<T> with(ErrorCode errorCode) {
        ApiResult<T> response = new ApiResult<>();
        response.code = errorCode.getCode();
        response.message = errorCode.getMessage();
        response.httpStatus = errorCode.getHttpStatus();
        response.data = null;
        response.timestamp = System.currentTimeMillis();
        return response;
    }

}
