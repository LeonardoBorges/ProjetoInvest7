package br.com.invest7.ProjetoInvest7.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculadoraFiisRequest {
    private BigDecimal aporteMensal;
    private BigDecimal quantidadeCotas;
    private int prazo;
    private boolean reinvestir;

}
