package com.automation.testing.steps;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.datafaker.Faker; // Importamos Datafaker

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps {
    Playwright playwright;
    APIRequestContext request;
    APIResponse response;
    String jsonBody;

    // Variables para guardar los datos inventados y validarlos luego
    String nombreAleatorio;
    String trabajoAleatorio;

    @Dado("que preparo los datos de un usuario con nombre y trabajo aleatorios")
    public void preparoDatosAleatorios() {
        playwright = Playwright.create();
        request = playwright.request().newContext();

        // ¡Magia! Creamos el generador de datos falsos
        Faker faker = new Faker();

        // Le pedimos un nombre completo y un título de trabajo inventados
        nombreAleatorio = faker.name().fullName();
        trabajoAleatorio = faker.job().title();

        System.out.println("🎭 Datafaker ha creado a: " + nombreAleatorio + " (" + trabajoAleatorio + ")");

        // Metemos esas variables en nuestro JSON
        jsonBody = "{\n" +
                "    \"name\": \"" + nombreAleatorio + "\",\n" +
                "    \"job\": \"" + trabajoAleatorio + "\"\n" +
                "}";
    }

    @Cuando("envío la petición POST a la API de JSONPlaceholder")
    public void envioPeticion() {
        response = request.post("https://jsonplaceholder.typicode.com/posts",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(jsonBody)
        );
    }

    @Entonces("el código de respuesta de la API debería ser {int}")
    public void verificoCodigo(int codigoEsperado) {
        assertEquals(codigoEsperado, response.status(), "El código no coincide");
    }

    @Y("la respuesta de la API debería contener el nombre generado")
    public void verificoNombreGenerado() {
        // Validamos que la API nos devuelve el mismo nombre aleatorio que creamos al principio
        assertTrue(response.text().contains(nombreAleatorio), "El nombre no está en la respuesta");
        playwright.close();
    }
}