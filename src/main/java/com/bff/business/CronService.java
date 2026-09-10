package com.bff.business;

import com.bff.business.dto.in.LoginDTORequest;
import com.bff.business.dto.out.TarefaDTOResponse;
import com.bff.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasFuturas(){
        log.info("--- CRON INICIADO ---");
        try {
            log.info("Autenticando usuario {} no microserviço de usuarios...", email);
            String token = login(converterParaRequestDTO());
            log.info("Login realizado com sucesso!");

            LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
            LocalDateTime horaFuturaMaisCinco = horaFutura.plusMinutes(5);

            log.info("Buscando tarefas agendadas entre {} e {}", horaFutura, horaFuturaMaisCinco);
            List<TarefaDTOResponse> listaTarefas = tarefaService.buscaTarefasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);

            log.info("Quantidade de tarefas encontradas: {}", listaTarefas != null ? listaTarefas.size() : 0);

            if (listaTarefas != null && !listaTarefas.isEmpty()) {
                listaTarefas.forEach(tarefa -> {
                    log.info("Enviando e-mail de notificacao para o usuario: {}", tarefa.getEmailUsuario());
                    emailService.enviaEmail(tarefa);

                    log.info("Atualizando status da tarefa ID {} para NOTIFICADO", tarefa.getId());
                    tarefaService.alteraStatusTarefa(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
                });
            } else {
                log.warn("Nenhuma tarefa encontrada na janela de tempo informada.");
            }

            log.info("--- CRON FINALIZADO COM SUCESSO ---");

        } catch (Exception e) {
            log.error("FALHA NA EXECUÇÃO DO CRON: ", e);
        }
    }

    public String login(LoginDTORequest loginDTORequest){
        return usuarioService.loginUsuario(loginDTORequest);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}