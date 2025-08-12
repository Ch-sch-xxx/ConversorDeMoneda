# Conversor de Monedas Java

![Java](https://img.shields.io/badge/Language-Java-blue)
![License](https://img.shields.io/badge/License-MIT-green)

---

## Descripción

Este proyecto es una **aplicación de consola en Java** para convertir valores entre diferentes monedas usando tasas de cambio en tiempo real obtenidas de la API [ExchangeRate-API](https://www.exchangerate-api.com/).  

El usuario puede elegir entre varias opciones de conversión, ingresar cantidades, y obtener resultados precisos. Todas las conversiones realizadas se registran y generan un reporte final en un archivo de texto.

---

## Características principales

- Consulta dinámica a la API para obtener tasas actualizadas.
- Manejo robusto de conversiones entre monedas específicas (USD, ARS, BRL, COP).
- Registro y generación de un reporte en archivo `.txt` con todas las conversiones hechas.
- Arquitectura modular con paquetes separados para consulta API, modelo de datos, reporte y lógica principal.
- Manejo de errores para entradas inválidas y problemas en la conexión con la API.

---

## Estructura del proyecto

| Paquete                         | Descripción                                      |
|---------------------------------|------------------------------------------------|
| `com.alura.desafio.ConversorMoneda.Principal` | Lógica principal y menú de interacción con usuario.      |
| `com.alura.desafio.ConversorMoneda.ConsultaApi` | Clase para realizar consultas HTTP a la API y fformatear JSON. |
| `com.alura.desafio.ConversorMoneda.Records`    | Modelos Java `record` para mapear la respuesta JSON de la API.     |
| `com.alura.desafio.ConversorMoneda.Reporte`    | Clase para gestionar el registro y escritura del reporte de conversiones. |

---

## Cómo usar

### Ejecución

1. Clonar o descargar el repositorio.
2. Asegurarse de tener la dependencia Gson configurada en el classpath.
3. Ejecutar la clase principal `ConversorApp`:


4. Seguir las instrucciones en consola:
- Seleccionar la opción de conversión deseada.
- Ingresar la cantidad a convertir.
5. Al terminar y elegir salir, se generará un archivo `reporte_conversiones.txt` en el directorio del proyecto con el registro de todas las conversiones.

---

## Clave API

Se usa la clave pública de prueba, la cual se puede adquirir personalmente desde la pagina https://www.exchangerate-api.com/
---

## Flujo general de trabajo

1. El usuario selecciona desde el menú el tipo de conversión deseada.
2. El programa consulta la API para obtener las tasas actuales según la moneda base.
3. Extrae la tasa de la moneda destino desde los datos recibidos.
4. Realiza la conversión y muestra el resultado por pantalla.
5. Registra la conversión realizada para el reporte.
6. Al salir, se guarda el reporte en un archivo de texto.

---



*Proyecto desarrollado para el desafío de conversor de monedas usando Java y API REST.*



