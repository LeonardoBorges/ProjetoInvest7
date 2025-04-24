package br.com.invest7.ProjetoInvest7.controller;

import br.com.invest7.ProjetoInvest7.client.ValoresAcoesClient;
import br.com.invest7.ProjetoInvest7.client.response.ValoresAcoesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buscarAcoes")
public class ValoresAcoesController {
    private final ValoresAcoesClient acoesClient;

    public ValoresAcoesController(ValoresAcoesClient acoesClient) {
        this.acoesClient = acoesClient;
    }

    @GetMapping
    public List<ValoresAcoesResponse> buscarValoresAcoes(){
        return acoesClient.buscarValoresAcoes();
    }
}
