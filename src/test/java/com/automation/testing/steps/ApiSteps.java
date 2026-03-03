package com.automation.testing.steps;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps {
    Playwright playwright;
    APIRequestContext request;
    APIResponse response;
    String jsonBody;

    @Dado("que preparo los datos del usuario {string} con el trabajo {string}")
    public void preparoDatos(String nombre, String trabajo) {
        playwright = Playwright.create();
        request = playwright.request().newContext();
        jsonBody = "{\n" +
                "    \"name\": \"" + nombre + "\",\n" +
                "    \"job\": \"" + trabajo + "\"\n" +
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

    @Y("la respuesta de la API debería contener el nombre {string}")
    public void verificoNombre(String nombreEsperado) {
        assertTrue(response.text().contains(nombreEsperado), "El nombre no está en la respuesta");
        playwright.close(); // Apagamos el motor de red al terminar
    }
}