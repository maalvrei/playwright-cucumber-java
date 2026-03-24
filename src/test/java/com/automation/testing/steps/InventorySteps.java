package com.automation.testing.steps;

import com.automation.testing.pages.InventoryPage;
import com.automation.testing.pages.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventorySteps {

    // Cogemos la página compartida desde Hooks
    InventoryPage inventoryPage = new InventoryPage(Hooks.page);
    LoginPage loginPage = new LoginPage(Hooks.page);

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
}