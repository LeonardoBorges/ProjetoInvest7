package br.com.invest7.ProjetoInvest7.controller.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CalculadoraRendaFixaRequest {
    private BigDecimal capitalInvestido;
    private BigDecimal aporteMensal;
    private int prazo;
}
