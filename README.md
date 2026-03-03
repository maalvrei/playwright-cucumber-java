# 🚀 Framework de Automatización E2E y API (Playwright + Java + Cucumber)

Este repositorio contiene un framework de automatización de pruebas híbrido, diseñado para validar tanto el **Frontend (UI)** como el **Backend (API)**. Está construido con las mejores prácticas de la industria, aplicando el patrón de diseño **Page Object Model (POM)** y **Behavior-Driven Development (BDD)**.

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Herramienta principal:** Playwright (Web & API)
* **Framework BDD:** Cucumber (Alineado v7.15.0)
* **Datos Dinámicos:** Datafaker (Data-Driven Testing)
* **Reportería Visual:** Allure Report
* **CI/CD Pipeline:** Jenkins
* **Motor & Gestor:** JUnit 5 / Maven / Google Gson

## 📐 Arquitectura y Patrones de Diseño

El proyecto está estructurado para ser altamente escalable y mantenible:
* **Page Object Model (POM):** Separación estricta entre la lógica de las pruebas y los localizadores visuales (`pages`).
* **BDD (Gherkin):** Casos de prueba escritos en lenguaje natural (español) dentro de la carpeta `features`.
* **Step Definitions:** Conexión modular entre los pasos en Gherkin y las acciones de Playwright (`steps`).
* **Generación de Datos Dinámicos:** Integración de Datafaker para evitar el hardcodeo de datos en las peticiones API y evitar falsos positivos en BD.
* **Infraestructura como Código:** Integración lista mediante `Jenkinsfile` para despliegues automatizados.

## 🧪 Tipos de Pruebas Implementadas

1.  🌐 **Pruebas UI (Frontend):** * Navegación automatizada y validación de elementos en la web de pruebas *SauceDemo*.
2.  ⚙️ **Pruebas API (Backend):** * Peticiones HTTP (GET/POST) a *JSONPlaceholder* con inyección de cargas útiles (payloads) generadas de forma aleatoria.
3.  🧬 **Pruebas Híbridas (E2E Avanzado):** * Extracción de datos reales desde la API (Parseo de JSON) e inyección de esos datos en la interfaz web durante la misma ejecución para realizar *Negative Testing*.

## 📁 Estructura del Proyecto

```text
src/
 ├── main/
 └── test/
      ├── java/com/automation/testing/
      │    ├── pages/          # Clases POM (Ej: LoginPage.java)
      │    ├── runners/        # Ejecutor de la suite y conexión con Allure
      │    └── steps/          # Definición de pasos BDD
      └── resources/
           ├── features/       # Archivos de pruebas en Gherkin (.feature)
           └── allure.properties # Configuración de reportería
Jenkinsfile                    # Pipeline de CI/CD declarativo

```

# 🚀 Cómo ver el Reporte Allure en local
* **Ejecuta la suite de pruebas desde la clase TestRunner.

* **Lanza el servidor web de reportería mediante Maven:

Bash
mvn allure:serve