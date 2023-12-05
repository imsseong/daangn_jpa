package me.seongim.daangn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //error
    ACCESS_DENIED(1001, HttpStatus.FORBIDDEN, "AccessDeniedException"),

    //user error
    USER_NOT_FOUND(1101, HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."),
    DUPLICATED_EMAIL(1102, HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    PASSWORD_NOT_MATCHED(1103, HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    IMAGE_UPLOAD_FAIL(1104, HttpStatus.BAD_REQUEST, "이미지 업로드에 실패했습니다")
    ;

    private final int code;
    private final HttpStatus httpStatus;

    private final String message;

    ErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
