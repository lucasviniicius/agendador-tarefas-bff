package com.bff.business.dto.out;

import com.bff.business.dto.out.EnderecoDTOResponse;
import com.bff.business.dto.out.TelefoneDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTOResponse {

    private String nome;
    private String email;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse> telefones;
}