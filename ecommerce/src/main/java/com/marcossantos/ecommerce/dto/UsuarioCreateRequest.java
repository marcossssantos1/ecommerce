package com.marcossantos.ecommerce.dto;

import com.marcossantos.ecommerce.enums.TipoUsuario;

import jakarta.validation.constraints.*;

public record UsuarioCreateRequest(

    @NotBlank
    String nome,

    @Email @NotBlank
    String email,

    @NotBlank @Size(min = 11, max = 11)
    String cpf,

    @NotBlank @Size(min = 6)
    String senha,

    String telefone,

    @NotBlank @Size(min = 8, max = 8)
    String cep,

    @NotBlank
    String logradouro,

    @NotBlank
    String numero,

    String complemento,

    @NotBlank
    String bairro,

    @NotBlank
    String cidade,

    @NotBlank @Size(min = 2, max = 2)
    String estado,

    @NotNull
    TipoUsuario tipo
) {}
