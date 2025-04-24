package br.com.invest7.ProjetoInvest7.client;

import br.com.invest7.ProjetoInvest7.client.response.ApiBrapiResponse;
import br.com.invest7.ProjetoInvest7.client.response.ValoresAcoesResponse;
import br.com.invest7.ProjetoInvest7.client.response.ViaCepEnderecoResponse;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraAcoesResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ValoresAcoesClient {
    private final RestTemplate restTemplate;
    private static final String API_TOKEN = "gRCqKmdqmfWauhKdrcUZdB";
    public ValoresAcoesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ValoresAcoesResponse> buscarValoresAcoes(){
        List<ValoresAcoesResponse > acoes = new ArrayList<>();
        List<String> acoesDesejadas = Arrays.asList("PETR4", "AMZN", "VIVA3", "VALE3", "SBSP3", "TSLA");
        for (String nomeAcao : acoesDesejadas){
            String url = "https://brapi.dev/api/quote/" + nomeAcao + "?token=" + API_TOKEN;
            ApiBrapiResponse acao = restTemplate.getForObject(url, ApiBrapiResponse.class);
            if (acao != null && acao.getResults() != null && !acao.getResults().isEmpty()) {
                acoes.add(acao.getResults().get(0));
            }

            //timer para as requisições
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        return acoes;
    }
}
