# 🚀 Framework de Automatización E2E y API (Playwright + Java + Cucumber)

Este repositorio contiene un framework de automatización de pruebas híbrido, diseñado para validar tanto el **Frontend (UI)** como el **Backend (API)**. Está construido con las mejores prácticas de la industria, aplicando el patrón de diseño **Page Object Model (POM)** y **Behavior-Driven Development (BDD)**.

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Herramienta principal:** Playwright (Web & API)
* **Framework BDD:** Cucumber
* **Motor de pruebas:** JUnit 5
* **Gestor de dependencias:** Maven
* **Manejo de JSON:** Google Gson

## 📐 Arquitectura y Patrones de Diseño

El proyecto está estructurado para ser altamente escalable y mantenible:
* **Page Object Model (POM):** Separación estricta entre la lógica de las pruebas y los localizadores visuales (`pages`).
* **BDD (Gherkin):** Casos de prueba escritos en lenguaje natural (español) dentro de la carpeta `features`, accesibles para perfiles técnicos y de negocio.
* **Step Definitions:** Conexión modular entre los pasos en Gherkin y las acciones de Playwright (`steps`).

## 🧪 Tipos de Pruebas Implementadas

1.  🌐 **Pruebas UI (Frontend):** * Navegación automatizada y validación de elementos en la web de pruebas *SauceDemo*.
    * Manejo de aserciones visuales (mensajes de error).
2.  ⚙️ **Pruebas API (Backend):** * Peticiones HTTP (GET/POST) a *JSONPlaceholder*.
    * Validación de códigos de estado (Status 201, 200) y lectura del cuerpo de la respuesta.
3.  🧬 **Pruebas Híbridas (E2E Avanzado):** * Extracción de datos reales desde la API (Parseo de JSON) e inyección de esos datos en la interfaz web durante la misma ejecución para realizar *Negative Testing*.

## 📁 Estructura del Proyecto

```text
src/
 ├── main/
 └── test/
      ├── java/com/automation/testing/
      │    ├── pages/          # Clases POM (Ej: LoginPage.java)
      │    ├── runners/        # Ejecutor de la suite (Ej: TestRunner.java)
      │    └── steps/          # Definición de pasos BDD (Ej: LoginSteps.java)
      └── resources/
           └── features/       # Archivos de pruebas en Gherkin (.feature)