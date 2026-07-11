package com.automation.testing.steps;

import com.automation.testing.pages.CheckoutPage;
import com.automation.testing.pages.InventoryPage;
import com.automation.testing.pages.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventorySteps {

    // Cogemos la página compartida desde Hooks
    InventoryPage inventoryPage = new InventoryPage(Hooks.page);
    LoginPage loginPage = new LoginPage(Hooks.page);
    CheckoutPage checkoutPage = new CheckoutPage(Hooks.page);

    @Entonces("debería ser redirigido a la página principal del inventario")
    public void deberiaSerRedirigidoAlInventario() {
        assertTrue(inventoryPage.estaEnInventario(), "El usuario no llegó a la página de inventario");
    }

    @Dado("que el usuario ha iniciado sesión correctamente con {string} y {string}")
    public void queElUsuarioHaIniciadoSesionCorrectamente(String usuario, String password) {
        loginPage.navegar();
        loginPage.hacerLogin(usuario, password);
    }

    @Cuando("añade la mochila {string} al carrito")
    public void anadeLaMochilaAlCarrito(String producto) {
        inventoryPage.anadirMochilaAlCarrito();
    }

    @Entonces("el icono del carrito debería mostrar un {string}")
    public void elIconoDelCarritoDeberiaMostrar(String cantidadEsperada) {
        String cantidadActual = inventoryPage.obtenerCantidadCarrito();
        assertEquals(cantidadEsperada, cantidadActual, "La cantidad en el carrito no coincide");
    }

    @Cuando("elimina la mochila del carrito")
    public void eliminaLaMochilaDelCarrito() {
        inventoryPage.eliminarMochilaDelCarrito();
    }

    @Cuando("se dirige al carrito y hace clic en Checkout")
    public void seDirigeAlCarritoYCheckout() {
        checkoutPage.irAlCarrito();
        checkoutPage.iniciarCheckout();
    }

    @Y("completa los datos de envío con {string}, {string} y {string}")
    public void completaDatosEnvio(String nombre, String apellido, String codigoPostal) {
        checkoutPage.completarDatosEnvio(nombre, apellido, codigoPostal);
    }

    @Y("hace clic en Continuar")
    public void haceClicEnContinuar() {
        checkoutPage.continuarCheckout();
    }

    @Y("hace clic en Finalizar")
    public void haceClicEnFinalizar() {
        checkoutPage.finalizarCheckout();
    }

    @Entonces("debería ver el mensaje de éxito {string}")
    public void deberiaVerElMensajeDeExito(String mensajeEsperado) {
        assertEquals(mensajeEsperado, checkoutPage.obtenerMensajeExito(), "El mensaje de éxito no coincide");
    }

    @Entonces("debería ver el mensaje de error en checkout que contiene {string}")
    public void deberiaVerMensajeErrorEnCheckout(String mensajeEsperado) {
        String errorVisible = checkoutPage.obtenerMensajeError();
        assertTrue(errorVisible.contains(mensajeEsperado), "El error de checkout no coincide: " + errorVisible);
    }

    @Cuando("cierra la sesión")
    public void cierraLaSesion() {
        inventoryPage.logout();
    }

    @Entonces("debería ser redirigido a la página de login")
    public void deberiaSerRedirigidoALogin() {
        // En SauceDemo redirige a la URL base
        assertTrue(Hooks.page.url().contains("saucedemo.com"), "No se redirigió a SauceDemo");
        assertTrue(Hooks.page.locator("[data-test='username']").isVisible(), "La página de login no está visible");
    }
}