package br.com.invest7.ProjetoInvest7.client;


import br.com.invest7.ProjetoInvest7.client.response.ViaCepEnderecoResponse;
import br.com.invest7.ProjetoInvest7.exception.EntradaErrorExeception;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {
    private final RestTemplate restTemplate;

    public ViaCepClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ViaCepEnderecoResponse buscarEnderecoPorCep(String cep) {
        if (cep == null || cep.isBlank()) {
            throw new EntradaErrorExeception("CEP não pode ser nulo ou vazio");
        }
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, ViaCepEnderecoResponse.class);
    }

}
