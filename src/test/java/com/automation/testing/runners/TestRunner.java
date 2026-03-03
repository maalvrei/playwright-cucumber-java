package com.automation.testing.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Le dice dónde están los textos .feature
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.automation.testing.steps") // Le dice dónde está el código Java
public class TestRunner {
}