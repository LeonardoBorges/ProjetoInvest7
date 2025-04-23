package br.com.invest7.ProjetoInvest7.controller.request;

import br.com.invest7.ProjetoInvest7.entity.enums.Genero;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class CadastrarUsuarioRequest {
    private String nome = null, cpf = null, endereco = null,
            email = null, senha = null, cep =null;
    private Genero genero = null;
    @NonNull
    private LocalDate dt_nasc = null;

    @Field("id_perfil")
    private Integer idPerfil;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NonNull
    public LocalDate getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(@NonNull LocalDate dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
}
