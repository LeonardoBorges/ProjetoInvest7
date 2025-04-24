package br.com.invest7.ProjetoInvest7.controller.response;

import lombok.Data;
import org.springframework.data.annotation.Id;


public record LoginResponse(String token, String Id, String nome) {}
