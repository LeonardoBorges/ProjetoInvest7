package br.com.invest7.ProjetoInvest7.controller.response;

import org.springframework.data.annotation.Id;

public class CadastrarUsuarioResponse {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
