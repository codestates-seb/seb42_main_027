package ynzmz.server.error.exception;

import lombok.Getter;

public enum ExceptionCode {


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
