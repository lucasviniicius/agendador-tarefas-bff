package com.bff.infrastructure.client;

import com.bff.business.dto.in.TarefaDTORequest;
import com.bff.business.dto.out.TarefaDTOResponse;
import com.bff.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendadortarefas", url = "${agendadortarefas.url}")
public interface TarefaClient {
    @PostMapping
    TarefaDTOResponse criaTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                 @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscaTarefaPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                 @RequestHeader(name = "Authorization", required = false) String token);

    @GetMapping
    List<TarefaDTOResponse> buscaTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token);

    @DeleteMapping
    void removeTarefaPorId(@RequestParam String id, @RequestHeader(name = "Authorization", required = false) String token);

    @PatchMapping
    TarefaDTOResponse alteraStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                        @RequestParam("id") String id,
                                        @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    TarefaDTOResponse alteraTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                  @RequestParam("id") String id,
                                  @RequestHeader(name = "Authorization", required = false) String token);
}
