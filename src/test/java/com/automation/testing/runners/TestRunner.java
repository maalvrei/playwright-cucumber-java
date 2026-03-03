package com.automation.testing.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME; // Importamos la propiedad de plugins

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Dónde están los .feature
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.automation.testing.steps") // Dónde está el código Java
// ¡NUEVO! Conectamos Cucumber con Allure y le pedimos que la consola se vea bonita ("pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class TestRunner {
}