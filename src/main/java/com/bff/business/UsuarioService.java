package com.bff.business;

import com.bff.business.dto.in.EnderecoDTORequest;
import com.bff.business.dto.in.LoginDTORequest;
import com.bff.business.dto.in.TelefoneDTORequest;
import com.bff.business.dto.in.UsuarioDTORequest;

import com.bff.business.dto.out.EnderecoDTOResponse;
import com.bff.business.dto.out.TelefoneDTOResponse;
import com.bff.business.dto.out.UsuarioDTOResponse;
import com.bff.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscaUsuarioPorEmail(String email, String token){
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuario(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
        return usuarioClient.atualizaUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long id, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atualizaEndereco(enderecoDTO, id, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long id, TelefoneDTORequest telefoneDTO, String token){
        return usuarioClient.atualizaTelefone(telefoneDTO, id, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO){
        return usuarioClient.cadastraEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO){
        return usuarioClient.cadastraTelefone(telefoneDTO, token);
    }
}