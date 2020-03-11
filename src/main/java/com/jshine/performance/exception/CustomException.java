package com.jshine.performance.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -3993376696547776573L;
    public CustomException(String msg) {
        super(msg);
    }
    public CustomException() {
        super();
    }
}
 class CustomUnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = -3993376696547776573L;
    public CustomUnauthorizedException(String msg) {
        super(msg);
    }
    public CustomUnauthorizedException() {
        super();
    }
}

