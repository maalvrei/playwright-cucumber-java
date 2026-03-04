package com.automation.testing.steps;

import com.automation.testing.pages.LoginPage;
import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    Playwright playwright;
    Browser browser;
    Page page;
    LoginPage loginPage;

    // @Before se ejecuta antes de CADA escenario de Cucumber (Arranca el navegador)
    @Before
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(500));
        page = browser.newPage();
        loginPage = new LoginPage(page); // Instanciamos nuestro POM
    }

    @Dado("que el usuario está en la página de login de SauceDemo")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        loginPage.navegar();
    }

    // Fíjate cómo pasamos los textos entre comillas ("") como parámetros (String)
    @Cuando("introduce el usuario {string} y la contraseña {string}")
    public void introduceUsuarioYContrasena(String usuario, String password) {
        loginPage.hacerLogin(usuario, password);
    }

    @Entonces("debería ver un mensaje de error que contiene {string}")
    public void deberiaVerMensajeDeError(String mensajeEsperado) {
        String errorVisible = loginPage.obtenerMensajeError();
        assertTrue(errorVisible.contains(mensajeEsperado), "El error no es el esperado.");
    }

    // @After se ejecuta al terminar para no dejar el navegador abierto
    @After
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}