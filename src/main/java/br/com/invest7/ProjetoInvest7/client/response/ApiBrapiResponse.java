package br.com.invest7.ProjetoInvest7.client.response;

import lombok.Data;

import java.util.List;

@Data
public class ApiBrapiResponse {

    private List<ValoresAcoesResponse> results;
}
