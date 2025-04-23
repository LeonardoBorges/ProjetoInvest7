package br.com.invest7.ProjetoInvest7.controller.request;

import br.com.invest7.ProjetoInvest7.entity.enums.Genero;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Data
public class AtualizarUsuarioRequest {
    private String nome = null, endereco = null,
            cep =null;
    private Genero genero = null;
    @NonNull
    private LocalDate dt_nasc = null;


}
