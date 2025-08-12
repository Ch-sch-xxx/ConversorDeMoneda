package com.alura.desafio.ConversorMoneda.ConsultaApi;

import com.alura.desafio.ConversorMoneda.Records.ConversorMoneda;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.ConnectException;

public class ConsultaMoneda {

    private static  String API_KEY = "tu-api-key-aqui";
    private static  String ENDPOINT = "https://v6.exchangerate-api.com/v6/";

    /**
     * Consulta las tasas de cambio para la moneda base indicada desde la API.
     * @param monedaBase Código de la moneda base (por ejemplo, "USD", "EUR")
     * @return ConversorMoneda con los datos formateados
     * @throws IOException Si ocurre un error de red
     * @throws InterruptedException Si la solicitud HTTP es interrumpida
     */
    public ConversorMoneda consultarTasas(String monedaBase) throws IOException, InterruptedException {

        String urlString = String.format("%s%s/latest/%s", ENDPOINT, API_KEY, monedaBase.toLowerCase());
        URI direccionApi = URI.create(urlString);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccionApi)
                .GET()
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode != 200) {
                System.err.printf("Error: Código HTTP %d recibido de la API.%n", statusCode);
                return null;
            }

            String responseBody = response.body();
            return new Gson().fromJson(responseBody, ConversorMoneda.class);

        } catch (HttpTimeoutException e) {
            System.err.println("Error: Tiempo de espera agotado al conectar con la API.");
            throw e;
        } catch (ConnectException e) {
            System.err.println("Error: No se pudo conectar con la API. Verifica tu conexión a Internet.");
            throw e;
        } catch (JsonSyntaxException e) {
            System.err.println("Error: La respuesta JSON de la API tiene un formato inesperado.");
            throw e;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error inesperado durante la consulta a la API: " + e.getMessage());
            throw e;
        }

        // No se cierra explícitamente HttpClient porque es manejado automáticamente.
    }
}
