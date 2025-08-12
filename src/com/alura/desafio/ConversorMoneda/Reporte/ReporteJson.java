package com.alura.desafio.ConversorMoneda.Reporte;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReporteJson {

    private final List<String> lineas = new ArrayList<>();

    // Método para agregar una línea de conversión al reporte
    public void agregarConversion(String origen, String destino, double cantidad, double resultado) {
        String linea = String.format("Convertir %.2f %s a %s = %.2f %s", cantidad, origen, destino, resultado, destino);
        lineas.add(linea);
    }

    // Método para guardar el reporte en un archivo de texto
    public void guardarArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Reporte de conversiones realizadas\n");
            writer.write("===============================\n");
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("Archivo guardado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
