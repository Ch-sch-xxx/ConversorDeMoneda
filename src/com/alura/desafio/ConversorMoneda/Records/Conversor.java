package com.alura.desafio.ConversorMoneda.Records;

import java.util.Map;

public record Conversor(String result,
                        String baseCode,
                        Map<String, Double> conversionRates
) {
}
