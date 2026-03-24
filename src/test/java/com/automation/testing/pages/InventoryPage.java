package com.automation.testing.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage {
    private final Page page;

    // Declaramos los localizadores
    private final Locator tituloInventario;
    private final Locator botonAnadirMochila;
    private final Locator iconoCarrito;

    // Constructor: Inicializa la página y mapea los elementos web
    public InventoryPage(Page page) {
        this.page = page;

        // Mapeo de selectores CSS (identificadores de SauceDemo)
        this.tituloInventario = page.locator(".title");
        this.botonAnadirMochila = page.locator("[data-test='add-to-cart-sauce-labs-backpack']");
        this.iconoCarrito = page.locator(".shopping_cart_badge");
    }

    // --- ACCIONES Y VALIDACIONES ---

    /**
     * Verifica si el usuario ha llegado correctamente a la página de inventario
     * comprobando si el título principal es visible.
     */
    public boolean estaEnInventario() {
        return tituloInventario.isVisible();
    }

    /**
     * Hace clic en el botón de añadir al carrito de la mochila específica.
     */
    public void anadirMochilaAlCarrito() {
        botonAnadirMochila.click();
    }

    /**
     * Obtiene el número que aparece en el globo rojo del carrito de la compra.
     */
    public String obtenerCantidadCarrito() {
        // Si el carrito está vacío, el elemento no existe en el DOM en SauceDemo,
        // así que devolvemos "0" si no es visible.
        if (!iconoCarrito.isVisible()) {
            return "0";
        }
        return iconoCarrito.innerText();
    }
}