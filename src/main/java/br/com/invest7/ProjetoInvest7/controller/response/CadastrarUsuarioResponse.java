package br.com.invest7.ProjetoInvest7.controller.response;

import org.springframework.data.annotation.Id;

public record CadastrarUsuarioResponse(String token, String id, String nome) {}
