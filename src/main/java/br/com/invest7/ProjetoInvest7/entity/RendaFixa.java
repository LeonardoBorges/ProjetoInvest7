package br.com.invest7.ProjetoInvest7.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
@Data
@Document("rendafixa")
public class RendaFixa {
    private String id_renda_fixa;
    private String nome_produto;
    private BigDecimal rentabilidade_bruta;
    private String tipo_produto;
    private Integer is_taxable;
}
