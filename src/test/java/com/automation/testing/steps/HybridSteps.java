package com.automation.testing.steps;

import com.automation.testing.pages.CheckoutPage;
import com.automation.testing.pages.LoginPage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HybridSteps {
    LoginPage loginPage = new LoginPage(Hooks.page);
    CheckoutPage checkoutPage = new CheckoutPage(Hooks.page);
    String usuarioExtraidoApi;
    String valorExtraidoApi;

    @Dado("que obtengo un nombre de usuario desde la API de JSONPlaceholder")
    public void obtengoUsuarioApi() {
        APIRequestContext request = Hooks.playwright.request().newContext();
        APIResponse response = request.get("https://jsonplaceholder.typicode.com/users/1");

        // Extraemos el nombre con Gson
        JsonObject jsonObject = JsonParser.parseString(response.text()).getAsJsonObject();
        usuarioExtraidoApi = jsonObject.get("username").getAsString();
        System.out.println("🤖 Usuario extraído de la API: " + usuarioExtraidoApi);
    }

    @Cuando("intento hacer login en SauceDemo con ese usuario y la contraseña {string}")
    public void intentoLoginConUsuarioApi(String password) {
        loginPage.navegar();
        // Usamos la variable guardada en el paso anterior
        loginPage.hacerLogin(usuarioExtraidoApi, password);
    }

    @Entonces("el sistema muestra un mensaje de error que contiene {string}")
    public void verificoMensajeError(String mensajeEsperado) {
        String errorVisible = loginPage.obtenerMensajeError();
        assertTrue(errorVisible.contains(mensajeEsperado), "El error no es el esperado.");
    }

    // --- NUEVOS PASOS GENÉRICOS HÍBRIDOS ---

    @Dado("que obtengo el valor del atributo {string} desde el endpoint {string} de la API")
    public void obtengoAtributoDesdeEndpoint(String atributo, String endpoint) {
        APIRequestContext request = Hooks.playwright.request().newContext();
        APIResponse response = request.get("https://jsonplaceholder.typicode.com" + endpoint);

        // Extraemos con Gson soportando paths anidados como address.zipcode
        String jsonText = response.text().trim();
        if (jsonText.startsWith("[")) {
            // Si es un array cogemos el primer elemento
            JsonObject jsonObject = JsonParser.parseString(jsonText).getAsJsonArray().get(0).getAsJsonObject();
            valorExtraidoApi = obtenerValorDeJson(jsonObject, atributo);
        } else {
            JsonObject jsonObject = JsonParser.parseString(jsonText).getAsJsonObject();
            valorExtraidoApi = obtenerValorDeJson(jsonObject, atributo);
        }
        System.out.println("🤖 Valor [" + atributo + "] extraído de la API desde " + endpoint + ": " + valorExtraidoApi);
    }

    @Cuando("intento hacer login en SauceDemo con ese valor como usuario y la contraseña {string}")
    public void intentoLoginConValorApi(String password) {
        loginPage.navegar();
        loginPage.hacerLogin(valorExtraidoApi, password);
    }

    @Cuando("intento hacer login en SauceDemo con el usuario {string} y ese valor como contraseña")
    public void intentoLoginConValorApiComoContrasena(String usuario) {
        loginPage.navegar();
        loginPage.hacerLogin(usuario, valorExtraidoApi);
    }

    @Cuando("completo el checkout en SauceDemo usando ese valor como código postal, con nombre {string} y apellido {string}")
    public void completoCheckoutConValorComoZipCode(String nombre, String apellido) {
        checkoutPage.irAlCarrito();
        checkoutPage.iniciarCheckout();
        checkoutPage.completarDatosEnvio(nombre, apellido, valorExtraidoApi);
        checkoutPage.continuarCheckout();
    }

    @Cuando("completo el checkout en SauceDemo usando ese valor como nombre, con apellido {string} y código postal {string}")
    public void completoCheckoutConValorComoNombre(String apellido, String codigoPostal) {
        checkoutPage.irAlCarrito();
        checkoutPage.iniciarCheckout();
        checkoutPage.completarDatosEnvio(valorExtraidoApi, apellido, codigoPostal);
        checkoutPage.continuarCheckout();
    }

    @Cuando("completo el checkout en SauceDemo usando ese valor como apellido, con nombre {string} y código postal {string}")
    public void completoCheckoutConValorComoApellido(String nombre, String codigoPostal) {
        checkoutPage.irAlCarrito();
        checkoutPage.iniciarCheckout();
        checkoutPage.completarDatosEnvio(nombre, valorExtraidoApi, codigoPostal);
        checkoutPage.continuarCheckout();
    }

    private String obtenerValorDeJson(JsonObject jsonObject, String path) {
        String[] parts = path.split("\\.");
        JsonObject current = jsonObject;
        for (int i = 0; i < parts.length - 1; i++) {
            current = current.getAsJsonObject(parts[i]);
        }
        return current.get(parts[parts.length - 1]).getAsString();
    }
}