package com.bff.controller;

import com.bff.business.TarefaService;
import com.bff.business.dto.in.TarefaDTORequest;
import com.bff.business.dto.out.TarefaDTOResponse;
import com.bff.business.enums.StatusNotificacaoEnum;
import com.bff.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar Tarefa", description = "Cria um nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<TarefaDTOResponse> criaTarefa(@RequestBody TarefaDTORequest tarefaDTO, @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.criaTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar Tarefas Por Período", description = "Busca lista de tarefas por período")
    @ApiResponse(responseCode = "200", description = "Lista de tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaTarefaPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                                                                        @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar Tarefas Por Email", description = "Busca lista de tarefas por ID")
    @ApiResponse(responseCode = "200", description = "Lista de tarefa encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscaTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Tarefa", description = "Deleta uma tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Void> removeTarefaPorId(@RequestParam String id, @RequestHeader(name = "Authorization", required = false) String token){
        tarefaService.removeTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar Status da Tarefa", description = "Altera status da tarefa")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<TarefaDTOResponse> alteraStatusTarefa(@RequestParam("status")StatusNotificacaoEnum status,
                                                               @RequestParam("id") String id,
                                                               @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.alteraStatusTarefa(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Alterar Tarefa", description = "Altera uma tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<TarefaDTOResponse> alteraTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                         @RequestParam("id") String id,
                                                         @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.alteraTarefa(tarefaDTO, id, token));
    }
}