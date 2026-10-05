package com.usuarios.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String menssagem) {
        super(menssagem);
    }

    public ResourceNotFoundException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
