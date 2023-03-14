package ynzmz.server.error.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {

    private final ExceptionCode exceptionCode;
    @Getter
    private ExceptionCode exceptionCode;


    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
