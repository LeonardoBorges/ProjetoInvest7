package br.com.invest7.ProjetoInvest7.client.response;

import lombok.Data;

@Data
public class ViaCepEnderecoResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
