package ynzmz.server.error.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private ExceptionCode exceptionCode;
    public CustomException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
