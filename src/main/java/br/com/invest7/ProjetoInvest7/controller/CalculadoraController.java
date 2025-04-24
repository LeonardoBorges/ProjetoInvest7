package br.com.invest7.ProjetoInvest7.controller;


import br.com.invest7.ProjetoInvest7.controller.request.CalculadoraAcoesRequest;
import br.com.invest7.ProjetoInvest7.controller.request.CalculadoraFiisRequest;
import br.com.invest7.ProjetoInvest7.controller.request.CalculadoraRendaFixaRequest;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraAcoesResponse;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraFiisResponse;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraRendaFixaResponse;
import br.com.invest7.ProjetoInvest7.service.CalculadoraService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/calculadora")
@RestController
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("/fiis")
    public List<CalculadoraFiisResponse> calculadoraFiis(@RequestBody @Valid CalculadoraFiisRequest fiisRequest){
        return calculadoraService.calculadoraFiis(fiisRequest);
    }

    @PostMapping("/acoes")
    public List<CalculadoraAcoesResponse> calculadoraAcoes(@RequestBody @Valid CalculadoraAcoesRequest acoesRequest){
        return calculadoraService.calculadoraAcoes(acoesRequest);
    }

    @PostMapping("/rendafixa")
    public List<CalculadoraRendaFixaResponse> calculadoraRendaFixa(@RequestBody @Valid CalculadoraRendaFixaRequest rendaFixaRequest){
        return calculadoraService.calculadoraRendaFixa(rendaFixaRequest);
    }

}
