package ynzmz.server.error.exception;

public class BusinessLogicException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
