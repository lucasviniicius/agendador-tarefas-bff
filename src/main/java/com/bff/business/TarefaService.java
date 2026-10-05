package com.bff.business;

import com.bff.business.dto.in.TarefaDTORequest;
import com.bff.business.dto.out.TarefaDTOResponse;
import com.bff.business.enums.StatusNotificacaoEnum;
import com.bff.infrastructure.client.TarefaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaClient tarefaClient;

    public TarefaDTOResponse criaTarefa(String token, TarefaDTORequest tarefaDTO){
        return tarefaClient.criaTarefa(tarefaDTO, token);
    }

    public List<TarefaDTOResponse> buscaTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return tarefaClient.buscaTarefaPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaDTOResponse> buscaTarefaPorEmail(String token){
        return tarefaClient.buscaTarefaPorEmail(token);
    }

    public void removeTarefaPorId(String id, String token){
        tarefaClient.removeTarefaPorId(id, token);
    }

    public TarefaDTOResponse alteraStatusTarefa(StatusNotificacaoEnum status, String id, String token){
        return tarefaClient.alteraStatusTarefa(status, id, token);
    }

    public TarefaDTOResponse alteraTarefa(TarefaDTORequest tarefaDTO, String id, String token){
        return tarefaClient.alteraTarefa(tarefaDTO, id, token);
    }
}