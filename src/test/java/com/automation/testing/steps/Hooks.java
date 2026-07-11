package com.automation.testing.steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    @BeforeAll
    public static void beforeAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(500));
    }

    @AfterAll
    public static void afterAll() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    // Se ejecuta antes de CADA escenario
    @Before
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
    }

    // Se ejecuta después de CADA escenario
    @After
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
    }
}