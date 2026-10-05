package com.bff.infrastructure.client;

import com.bff.business.dto.in.EnderecoDTORequest;
import com.bff.business.dto.in.LoginDTORequest;
import com.bff.business.dto.in.TelefoneDTORequest;
import com.bff.business.dto.in.UsuarioDTORequest;
import com.bff.business.dto.out.EnderecoDTOResponse;
import com.bff.business.dto.out.TelefoneDTOResponse;
import com.bff.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {
    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    public String login(@RequestBody LoginDTORequest usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuario(@PathVariable String email,
                       @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                      @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                        @RequestHeader(name = "Authorization", required = false) String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                        @RequestHeader(name = "Authorization", required = false) String token);
}