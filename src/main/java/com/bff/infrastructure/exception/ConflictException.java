package com.usuarios.infrastructure.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String menssagem) {
        super(menssagem);
    }

    public ConflictException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
