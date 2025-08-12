package com.alura.desafio.ConversorMoneda.Principal;


import com.alura.desafio.ConversorMoneda.ConsultaApi.ConsultaMoneda;
import com.alura.desafio.ConversorMoneda.Records.ConversorMoneda;

import java.io.IOException;

public class ConversorApp {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Iniciando el conversor de monedas...");
        ConsultaMoneda consultaMoneda = new ConsultaMoneda();
        ConversorMoneda conversorMoneda = consultaMoneda.consultarTasas("clp");
        System.out.println(conversorMoneda);

    }
}