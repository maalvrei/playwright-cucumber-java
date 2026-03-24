package com.automation.testing.steps;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.datafaker.Faker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiSteps {

    APIRequestContext request;
    APIResponse response;

    // Variables para el POST
    String jsonBody;
    String nombreAleatorio;
    String trabajoAleatorio;

    // Variable para el GET y DELETE
    String baseUrl;

    // --- ESCENARIO 1: POST (El que ya tenías) ---

    @Dado("que preparo los datos de un usuario con nombre y trabajo aleatorios")
    public void preparoDatosAleatorios() {
        // Usamos el Playwright global de nuestra clase Hooks en lugar de crear uno nuevo
        request = Hooks.playwright.request().newContext();

        Faker faker = new Faker();
        nombreAleatorio = faker.name().fullName();
        trabajoAleatorio = faker.job().title();

        System.out.println("🎭 Datafaker ha creado a: " + nombreAleatorio + " (" + trabajoAleatorio + ")");

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

    @Y("la respuesta de la API debería contener el nombre generado")
    public void verificoNombreGenerado() {
        assertTrue(response.text().contains(nombreAleatorio), "El nombre no está en la respuesta");
        // Eliminado el playwright.close() porque Hooks ya se encarga de cerrarlo al final
    }

    // --- ESCENARIOS 2 y 3: GET y DELETE (Los nuevos) ---

    @Dado("que la URL base de la API es {string}")
    public void queLaUrlBaseDeLaApiEs(String url) {
        this.baseUrl = url;
        // Inicializamos el contexto de red usando el motor de Hooks
        request = Hooks.playwright.request().newContext();
    }

    @Cuando("envío una petición GET al endpoint {string}")
    public void envioUnaPeticionGetAlEndpoint(String endpoint) {
        System.out.println("Enviando GET a: " + baseUrl + endpoint);
        response = request.get(baseUrl + endpoint);
    }

    @Cuando("envío una petición DELETE al endpoint {string}")
    public void envioUnaPeticionDeleteAlEndpoint(String endpoint) {
        System.out.println("Enviando DELETE a: " + baseUrl + endpoint);
        response = request.delete(baseUrl + endpoint);
    }

    @Entonces("el cuerpo de la respuesta en formato JSON debería contener el atributo {string}")
    public void elCuerpoDeLaRespuestaDeberiaContenerElAtributo(String atributoEsperado) {
        assertTrue(response.text().contains("\"" + atributoEsperado + "\":"),
                "El JSON de respuesta no contiene el atributo: " + atributoEsperado);
    }

    // --- MÉTODO COMPARTIDO POR TODOS LOS ESCENARIOS ---

    @Entonces("el código de respuesta de la API debería ser {int}")
    public void verificoCodigo(int codigoEsperado) {
        assertEquals(codigoEsperado, response.status(), "El código HTTP no coincide");
    }
}