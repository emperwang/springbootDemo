package com.wk.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
public class NfvoException extends Exception {

    private Integer errorCode;

    protected  Integer statusCode = 500;

    private String detailMessage = null;

    public NfvoException(Integer code){
        super(ExceptionMapping.getInstance().getExceptionMsg(code));
        this.errorCode = code;
    }

    public NfvoException(Integer code,Throwable cause){
        super(ExceptionMapping.getInstance().getExceptionMsg(code),cause);
        errorCode = code;
        log.error("NFVO occurs exception",cause);
    }

    public NfvoException(Integer code,String message){
        super(message);
        errorCode = code;
        if (message == null){
            this.detailMessage = ExceptionMapping.getInstance()
                    .getExceptionMsg(code);
        }
    }

    public NfvoException(Integer code, String message, Throwable cause){
        super(message,cause);
        errorCode = code;
        if (message == null){
            this.detailMessage = ExceptionMapping.getInstance()
                    .getExceptionMsg(code);
        }
        log.error("NFVO occurs exception",cause);
    }

    public NfvoException(Integer errorCode,Integer statusCode,String message){
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public NfvoException(Integer errorCode,Integer statusCode,Throwable cause){
        super(cause);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

}
