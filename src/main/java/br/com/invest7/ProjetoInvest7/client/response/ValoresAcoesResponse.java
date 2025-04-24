package br.com.invest7.ProjetoInvest7.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValoresAcoesResponse(
        @JsonProperty("longName") String longName,
        @JsonProperty("regularMarketChange") double regularMarketChange,
        @JsonProperty("regularMarketPrice") double regularMarketPrice,
        @JsonProperty("regularMarketDayHigh") double regularMarketDayHigh,
        @JsonProperty("regularMarketDayLow") double regularMarketDayLow,
        @JsonProperty("regularMarketOpen") double regularMarketOpen,
        @JsonProperty("symbol") String symbol
) {
}
