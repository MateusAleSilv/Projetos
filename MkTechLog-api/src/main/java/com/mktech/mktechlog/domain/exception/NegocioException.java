package com.mktech.mktechlog.domain.exception;

public class NegocioException extends RuntimeException{

    private static final long seralVersionUID = 1L;

    public NegocioException(String message){
        super(message);
    }
}
