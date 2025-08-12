package com.alura.desafio.ConversorMoneda.Records;

import java.util.Map;

public record ConversorMoneda(String result,
                              String base_code,
                              Map<String, Double> conversion_rates) {
}
