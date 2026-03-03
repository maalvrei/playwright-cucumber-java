package com.automation.testing.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // Localizadores
    private String inputUsuario = "[data-test='username']";
    private String inputPassword = "[data-test='password']";
    private String botonLogin = "[data-test='login-button']";
    private String mensajeError = "[data-test='error']"; // ¡NUEVO! Dónde sale el error

    public LoginPage(Page page) {
        this.page = page;
    }

    public void navegar() {
        page.navigate("https://www.saucedemo.com/");
    }

    public void hacerLogin(String usuario, String password) {
        page.locator(inputUsuario).fill(usuario);
        page.locator(inputPassword).fill(password);
        page.locator(botonLogin).click();
    }

    // ¡NUEVO! Método para que el test pueda leer qué error ha salido
    public String obtenerMensajeError() {
        return page.locator(mensajeError).textContent();
    }
}