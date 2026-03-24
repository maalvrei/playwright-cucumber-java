package com.automation.testing.steps;

import com.automation.testing.pages.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    // Cogemos la página compartida desde la clase Hooks
    LoginPage loginPage = new LoginPage(Hooks.page);

    @Dado("que el usuario está en la página de login de SauceDemo")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        loginPage.navegar();
    }

    @Cuando("introduce el usuario {string} y la contraseña {string}")
    public void introduceUsuarioYContrasena(String usuario, String password) {
        loginPage.hacerLogin(usuario, password);
    }

    @Entonces("debería ver un mensaje de error que contiene {string}")
    public void deberiaVerMensajeDeError(String mensajeEsperado) {
        String errorVisible = loginPage.obtenerMensajeError();
        assertTrue(errorVisible.contains(mensajeEsperado), "El error no es el esperado.");
    }
}