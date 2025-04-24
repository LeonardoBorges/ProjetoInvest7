package br.com.invest7.ProjetoInvest7.service;

import br.com.invest7.ProjetoInvest7.controller.request.CalculadoraAcoesRequest;
import br.com.invest7.ProjetoInvest7.controller.request.CalculadoraFiisRequest;
import br.com.invest7.ProjetoInvest7.controller.request.CalculadoraRendaFixaRequest;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraAcoesResponse;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraFiisResponse;
import br.com.invest7.ProjetoInvest7.controller.response.CalculadoraRendaFixaResponse;
import br.com.invest7.ProjetoInvest7.entity.Acoes;
import br.com.invest7.ProjetoInvest7.entity.Fiis;
import br.com.invest7.ProjetoInvest7.entity.RendaFixa;
import br.com.invest7.ProjetoInvest7.exception.AcoesNaoEncontradasExeption;
import br.com.invest7.ProjetoInvest7.exception.FiisNaoEncontradoExeption;
import br.com.invest7.ProjetoInvest7.repository.Acoes.AcoesRepository;
import br.com.invest7.ProjetoInvest7.repository.Fiis.FiisRepository;
import br.com.invest7.ProjetoInvest7.repository.RendaFixa.RendaFixaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@Service
public class CalculadoraService {
    private final FiisRepository fiisRepository;
    private final AcoesRepository acoesRepository;
    private final RendaFixaRepository rendaFixaRepository;

    public CalculadoraService(FiisRepository fiisRepository, AcoesRepository acoesRepository, RendaFixaRepository rendaFixaRepository) {
        this.fiisRepository = fiisRepository;
        this.acoesRepository = acoesRepository;
        this.rendaFixaRepository = rendaFixaRepository;
    }

    public List<CalculadoraFiisResponse> calculadoraFiis(CalculadoraFiisRequest fiisRequest){
        List<Fiis> fiisList = fiisRepository.findAll();
        List<CalculadoraFiisResponse> fiiSimulado = new ArrayList<>();
        if (fiisList == null || fiisList.isEmpty()){
            throw new FiisNaoEncontradoExeption("Nenhum FII encontrado para simulação");
        }

        for (Fiis fii : fiisList) {
            BigDecimal saldoDividendos = BigDecimal.ZERO;
            BigDecimal saldoAporte = BigDecimal.ZERO;
            BigDecimal dividendoPorCota = fii.getDividend_yeld();
            BigDecimal valorAporte = fiisRequest.getAporteMensal();
            BigDecimal precoCota = fii.getPreco_fiis();
            BigDecimal desvioCotas = fii.getDesvio_cotas().divide(BigDecimal.valueOf(100));
            BigDecimal desvioDividendos = fii.getDesvio_dividendos().divide(BigDecimal.valueOf(100));

            int meses = fiisRequest.getPrazo();
            BigDecimal quantidadeCotas = fiisRequest.getQuantidadeCotas();
            boolean reinvestir = fiisRequest.isReinvestir();


            for (int mes = 1; mes <= meses; mes++) {
                // Calculando os dividendos recebidos
                BigDecimal dividendosRecebidos = quantidadeCotas.multiply(dividendoPorCota);

                if (reinvestir) {
                    saldoDividendos = saldoDividendos.add(dividendosRecebidos).add(valorAporte);
                } else {
                    saldoDividendos = saldoDividendos.add(dividendosRecebidos);
                    saldoAporte = saldoAporte.add(valorAporte);
                }

                if (reinvestir) {
                    BigDecimal novasCotas = saldoDividendos.divide(precoCota, 0, RoundingMode.DOWN);
                    quantidadeCotas = quantidadeCotas.add(novasCotas);
                    saldoDividendos = saldoDividendos.subtract(novasCotas.multiply(precoCota));
                } else {
                    BigDecimal novasCotasAporte = saldoAporte.divide(precoCota, 0, RoundingMode.DOWN);
                    quantidadeCotas = quantidadeCotas.add(novasCotasAporte);
                    saldoAporte = saldoAporte.subtract(novasCotasAporte.multiply(precoCota));
                }
            }

            BigDecimal dividendosMensais = quantidadeCotas.multiply(dividendoPorCota);


            CalculadoraFiisResponse response = new CalculadoraFiisResponse();
            response.setNomeProd(fii.getNome_prod());
            response.setPrecoFiis(precoCota);
            response.setDividendYield(dividendoPorCota);
            response.setQtdCotas(quantidadeCotas);
            response.setDividendosMensais(dividendosMensais);
            response.setSaldoCotas(quantidadeCotas.multiply(precoCota) );
            response.setSaldoDividendos(saldoDividendos);


            fiiSimulado.add(response);

        }

        return fiiSimulado;

    }



    public List<CalculadoraAcoesResponse> calculadoraAcoes(CalculadoraAcoesRequest acoesRequest){
        List<Acoes> acoesList = acoesRepository.findAll();
        List<CalculadoraAcoesResponse> acoesSimuladas = new ArrayList<>();
        if (acoesList == null || acoesList.isEmpty()){
            throw new AcoesNaoEncontradasExeption("Nenhuma Acao encontrada para simulação");
        }
        BigDecimal capital = acoesRequest.getCapitalInvestido();
        for (Acoes acao : acoesList) {

            BigDecimal precoCompra = acao.getPreco_acao();
            BigDecimal txIr = acao.getTx_ir();
            BigDecimal desvio = acao.getDesvio().divide(BigDecimal.valueOf(100));

            BigDecimal quantidadeAcao = capital.divide(precoCompra, 2, RoundingMode.DOWN);
            BigDecimal custoTotalCompra = precoCompra.multiply(quantidadeAcao);

            // Variação aleatória como BigDecimal
            double random = (Math.random() * 2) - 1; // entre -1 e 1
            BigDecimal variacao = desvio.multiply(BigDecimal.valueOf(random));
            BigDecimal valorAcaoVenda = precoCompra.multiply(BigDecimal.ONE.add(variacao));

            BigDecimal valorTotalVenda = valorAcaoVenda.multiply(quantidadeAcao).setScale(2, RoundingMode.HALF_UP);
            BigDecimal saldo = valorTotalVenda.subtract(custoTotalCompra);
            BigDecimal troco = capital.subtract(custoTotalCompra);

            if (saldo.compareTo(BigDecimal.valueOf(20000)) > 0) {
                saldo = saldo.subtract(saldo.multiply(txIr)  .setScale(2, RoundingMode.HALF_UP));
            }


            CalculadoraAcoesResponse acoes =  new CalculadoraAcoesResponse(acao.getNome_prod(), quantidadeAcao, capital,
                    saldo, custoTotalCompra, valorTotalVenda, troco, acao.getPreco_acao());

            acoesSimuladas.add(acoes);
        }

        return acoesSimuladas;
    }


    public List<CalculadoraRendaFixaResponse> calculadoraRendaFixa(CalculadoraRendaFixaRequest rendaFixaRequest){
        List<RendaFixa> rendasList = rendaFixaRepository.findAll();
        List<CalculadoraRendaFixaResponse> rendasSimuladas = new ArrayList<>();
        int meses = rendaFixaRequest.getPrazo();
        BigDecimal valorInicial = rendaFixaRequest.getCapitalInvestido();
        BigDecimal aporteMensal =  rendaFixaRequest.getAporteMensal();
        for (RendaFixa produto : rendasList) {
            // Convert annual rate to monthly decimal format
            BigDecimal annualRate = produto.getRentabilidade_bruta()
                    .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

            BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
            BigDecimal totalInvestido = valorInicial.add(aporteMensal.multiply(BigDecimal.valueOf(meses)));

            // Future value calculation using compound interest formula
            BigDecimal futureValueInitial = valorInicial.multiply(
                    BigDecimal.ONE.add(monthlyRate).pow(meses)
            );

            // Future value of monthly contributions (annuity formula)
            BigDecimal futureValueContributions = BigDecimal.ZERO;
            if (monthlyRate.compareTo(BigDecimal.ZERO) > 0) {
                futureValueContributions = aporteMensal.multiply(
                        BigDecimal.ONE.add(monthlyRate).pow(meses).subtract(BigDecimal.ONE)
                ).divide(monthlyRate, RoundingMode.HALF_UP);
            } else {
                futureValueContributions = aporteMensal.multiply(BigDecimal.valueOf(meses));
            }

            BigDecimal valorFinal = futureValueInitial.add(futureValueContributions)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal rendimentoBruto = valorFinal.subtract(totalInvestido);

            // Tax calculations
            int dias = meses * 30;
            BigDecimal irRate = calcularTaxaIR(dias, produto.getIs_taxable());
            BigDecimal impostoIR = rendimentoBruto.multiply(irRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal rendimentoLiquido = rendimentoBruto.subtract(impostoIR)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal valorTotal = totalInvestido.add(rendimentoLiquido)
                    .setScale(2, RoundingMode.HALF_UP);

            // Calculate percentage profit
            String percentLucro;
            if (totalInvestido.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal percent = rendimentoLiquido
                        .divide(totalInvestido, 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .setScale(2, RoundingMode.HALF_UP);
                percentLucro = percent + "%";
            } else {
                percentLucro = "0%";
            }


            CalculadoraRendaFixaResponse response = new CalculadoraRendaFixaResponse(produto.getNome_produto(),totalInvestido ,
                    rendimentoBruto, impostoIR, rendimentoLiquido, valorTotal, percentLucro);
            rendasSimuladas.add(response);

        }


        return rendasSimuladas;
    }

    public static BigDecimal calcularTaxaIR(int diasInvestimento, int isTaxable) {
        if (isTaxable == 0) return BigDecimal.ZERO;

        // Progressive IR table for Brazil (2024)
        if (diasInvestimento <= 180) return new BigDecimal("0.225");
        else if (diasInvestimento <= 360) return new BigDecimal("0.20");
        else if (diasInvestimento <= 720) return new BigDecimal("0.175");
        else return new BigDecimal("0.15");
    }
}
