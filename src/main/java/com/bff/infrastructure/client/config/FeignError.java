package com.bff.infrastructure.client.config;

import com.bff.infrastructure.exception.BusinessException;
import com.bff.infrastructure.exception.ConflictException;
import com.bff.infrastructure.exception.IllegalArgumentException;
import com.bff.infrastructure.exception.ResourceNotFoundException;
import com.bff.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response){
        String mensagemErro = mensagemErro(response);

        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Erro: " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro: " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro: " + mensagemErro);
            default:
                return new BusinessException("Requisição inválida.");
        }
    }

    public String mensagemErro(Response response){
        try {
            String mensagemErro = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return mensagemErro;
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
}
