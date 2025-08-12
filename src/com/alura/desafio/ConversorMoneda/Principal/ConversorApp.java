package com.alura.desafio.ConversorMoneda.Principal;

import com.alura.desafio.ConversorMoneda.ConsultaApi.ConsultaMoneda;
import com.alura.desafio.ConversorMoneda.Records.ConversorMoneda;
import com.alura.desafio.ConversorMoneda.Reporte.ReporteJson;

import java.io.IOException;
import java.util.Scanner;

public class ConversorApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ConsultaMoneda consultaMoneda = new ConsultaMoneda();
        ReporteJson reporte = new ReporteJson();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n---------- Conversor de Monedas ----------");
            System.out.println("Selecciona una opción:");
            System.out.println("1 - Dólar USD a Peso Argentino ARS");
            System.out.println("2 - Peso Argentino ARS a Dólar USD");
            System.out.println("3 - Dólar USD a Real Brasileño BRL");
            System.out.println("4 - Real Brasileño BRL a Dólar USD");
            System.out.println("5 - Dólar USD a Peso Colombiano COP");
            System.out.println("6 - Peso Colombiano COP a Dólar USD");
            System.out.println("7 - Salir");
            System.out.print("Tu opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingresa un número.");
                continue;
            }

            String monedaOrigen = null;
            String monedaDestino = null;

            switch (opcion) {
                case 1 -> { monedaOrigen = "USD"; monedaDestino = "ARS"; }
                case 2 -> { monedaOrigen = "ARS"; monedaDestino = "USD"; }
                case 3 -> { monedaOrigen = "USD"; monedaDestino = "BRL"; }
                case 4 -> { monedaOrigen = "BRL"; monedaDestino = "USD"; }
                case 5 -> { monedaOrigen = "USD"; monedaDestino = "COP"; }
                case 6 -> { monedaOrigen = "COP"; monedaDestino = "USD"; }
                case 7 -> {
                    salir = true;
                    System.out.println("Saliendo. Guardando reporte...");
                    break;
                }
                default -> {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    continue;
                }
            }

            if (salir) break;

            System.out.print("Ingrese la cantidad a convertir: ");
            double cantidad;
            try {
                cantidad = Double.parseDouble(scanner.nextLine());
                if (cantidad <= 0) {
                    System.out.println("Cantidad debe ser un número positivo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Intenta de nuevo.");
                continue;
            }

            try {
                // Consulta las tasas para la moneda origen
                ConversorMoneda tasaCambio = consultaMoneda.consultarTasas(monedaOrigen);
                if (tasaCambio == null) {
                    System.out.println("No se pudo obtener tasas para la moneda " + monedaOrigen);
                    continue;
                }

                Double tasa = tasaCambio.conversion_rates().get(monedaDestino);
                if (tasa == null) {
                    System.out.println("No se encontró la tasa para la moneda destino " + monedaDestino);
                    continue;
                }

                double resultado = cantidad * tasa;
                System.out.printf("Resultado: %.2f %s equivalen a %.2f %s%n",
                        cantidad, monedaOrigen, resultado, monedaDestino);

                // Agregar registro para reporte
                reporte.agregarConversion(monedaOrigen, monedaDestino, cantidad, resultado);

            } catch (IOException | InterruptedException e) {
                System.out.println("Error consultando la API: " + e.getMessage());
            }
        }

        // Guardar reporte al salir
        reporte.guardarArchivo("reporte_conversiones.txt");
        System.out.println("Reporte guardado. ¡Gracias por usar el conversor!");
        scanner.close();
    }
}
