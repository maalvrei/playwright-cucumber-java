package com.automation.testing.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;

    // Localizadores
    private final Locator botonCarrito;
    private final Locator botonCheckout;
    private final Locator inputFirstName;
    private final Locator inputLastName;
    private final Locator inputPostalCode;
    private final Locator botonContinue;
    private final Locator botonFinish;
    private final Locator mensajeError;
    private final Locator cabeceraExito;

    public CheckoutPage(Page page) {
        this.page = page;
        this.botonCarrito = page.locator(".shopping_cart_link");
        this.botonCheckout = page.locator("[data-test='checkout']");
        this.inputFirstName = page.locator("[data-test='firstName']");
        this.inputLastName = page.locator("[data-test='lastName']");
        this.inputPostalCode = page.locator("[data-test='postalCode']");
        this.botonContinue = page.locator("[data-test='continue']");
        this.botonFinish = page.locator("[data-test='finish']");
        this.mensajeError = page.locator("[data-test='error']");
        this.cabeceraExito = page.locator(".complete-header");
    }

    public void irAlCarrito() {
        botonCarrito.click();
    }

    public void iniciarCheckout() {
        botonCheckout.click();
    }

    public void completarDatosEnvio(String nombre, String apellido, String codigoPostal) {
        inputFirstName.fill(nombre);
        inputLastName.fill(apellido);
        inputPostalCode.fill(codigoPostal);
    }

    public void continuarCheckout() {
        botonContinue.click();
    }

    public void finalizarCheckout() {
        botonFinish.click();
    }

    public String obtenerMensajeError() {
        return mensajeError.textContent();
    }

    public String obtenerMensajeExito() {
        return cabeceraExito.textContent();
    }
}
