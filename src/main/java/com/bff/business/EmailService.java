package com.bff.business;


import com.bff.business.dto.out.TarefaDTOResponse;
import com.bff.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefaDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }


}