package com.wk.constants;

public enum  ServletExKey {
    NoHandlerFoundException("NoHandlerFoundException",ExceptionKey.NoHandlerFoundExceptionKey),
    HttpRequestMethodNotSupportedException("HttpRequestMethodNotSupportedException",ExceptionKey.HttpRequestMethodNotSupportedExceptionKey),
    HttpMediaTypeNotSupportedException("HttpMediaTypeNotSupportedException",ExceptionKey.HttpMediaTypeNotSupportedExceptionKey),
    MissingPathVariableException("MissingPathVariableException",ExceptionKey.MissingPathVariableExceptionKey),
    MissingServletRequestParameterException("MissingServletRequestParameterException",ExceptionKey.MissingServletRequestParameterExceptionKey),
    TypeMismatchException("TypeMismatchException",ExceptionKey.TypeMismatchExceptionKey),
    HttpMessageNotReadableException("HttpMessageNotReadableException",ExceptionKey.HttpMessageNotReadableExceptionKey),
    HttpMessageNotWritableException("HttpMessageNotWritableException",ExceptionKey.HttpMessageNotWritableExceptionKey),
    HttpMediaTypeNotAcceptableException("HttpMediaTypeNotAcceptableException",ExceptionKey.HttpMediaTypeNotAcceptableExceptionKey),
    ServletRequestBindingException("ServletRequestBindingException",ExceptionKey.ServletRequestBindingExceptionKey),
    ConversionNotSupportedException("ConversionNotSupportedException",ExceptionKey.ConversionNotSupportedExceptionKey),
    MissingServletRequestPartException("MissingServletRequestPartException",ExceptionKey.MissingServletRequestPartExceptionKey),
    AsyncRequestTimeoutException("AsyncRequestTimeoutException",ExceptionKey.AsyncRequestTimeoutExceptionKey);
    private String name;
    private int key;

    ServletExKey(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public static int resove(String name){
        int ret = -1;
        for (ServletExKey tmp : values()) {
            if (tmp.name.equalsIgnoreCase(name)){
                ret = tmp.key;
                break;
            }
        }
        return ret;
    }
}
