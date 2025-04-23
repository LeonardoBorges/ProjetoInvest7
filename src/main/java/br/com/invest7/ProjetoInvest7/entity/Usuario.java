package br.com.invest7.ProjetoInvest7.entity;

import br.com.invest7.ProjetoInvest7.entity.enums.Genero;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.NonNull;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;


@Document("usuario")
public class Usuario {
    private String nome = null, cpf = null, endereco = null,
            email = null, senha = null, descricao_perfil =null, cep = null;

    private Genero genero = null;

    @NonNull
    private LocalDate dt_nasc = null;
    @Id
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescricao_perfil() {
        return descricao_perfil;
    }

    public void setDescricao_perfil(String descricao_perfil) {
        this.descricao_perfil = descricao_perfil;
    }

    @NonNull
    public LocalDate getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(@NonNull LocalDate dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getId_user() {
        return id;
    }

    public void setId_user(String id_user) {
        this.id = id_user;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
}
