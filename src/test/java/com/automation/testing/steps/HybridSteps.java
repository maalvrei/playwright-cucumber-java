package com.automation.testing.steps;

import com.automation.testing.pages.LoginPage;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.*;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HybridSteps {
    Playwright playwright;
    Browser browser;
    Page page;
    LoginPage loginPage;
    String usuarioExtraidoApi;

    @Dado("que obtengo un nombre de usuario desde la API de JSONPlaceholder")
    public void obtengoUsuarioApi() {
        playwright = Playwright.create();
        APIRequestContext request = playwright.request().newContext();
        APIResponse response = request.get("https://jsonplaceholder.typicode.com/users/1");

        // Extraemos el nombre con Gson
        JsonObject jsonObject = JsonParser.parseString(response.text()).getAsJsonObject();
        usuarioExtraidoApi = jsonObject.get("username").getAsString();
        System.out.println("🤖 Usuario extraído de la API: " + usuarioExtraidoApi);
    }

    @Cuando("intento hacer login en SauceDemo con ese usuario y la contraseña {string}")
    public void intentoLoginConUsuarioApi(String password) {
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(500));
        page = browser.newPage();
        loginPage = new LoginPage(page);

        loginPage.navegar();
        // Usamos la variable guardada en el paso anterior
        loginPage.hacerLogin(usuarioExtraidoApi, password);
    }

    @Entonces("el sistema muestra un mensaje de error que contiene {string}")
    public void verificoMensajeError(String mensajeEsperado) {
        String errorVisible = loginPage.obtenerMensajeError();
        assertTrue(errorVisible.contains(mensajeEsperado), "El error no es el esperado.");

        // Limpiamos la prueba
        browser.close();
        playwright.close();
    }
}