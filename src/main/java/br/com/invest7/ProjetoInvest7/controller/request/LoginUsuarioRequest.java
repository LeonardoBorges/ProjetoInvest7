package br.com.invest7.ProjetoInvest7.controller.request;

import lombok.Data;

@Data
public class LoginUsuarioRequest {
    private String email;
    private String senha;

}
