# 🚀 Framework de Automatización E2E y API (Playwright + Java + Cucumber)

[![Allure Report](https://img.shields.io/badge/Allure_Report-Live_Results-brightgreen.svg)](https://maalvrei.github.io/playwright-cucumber-java/)

Este repositorio contiene un framework de automatización de pruebas híbrido, diseñado para validar tanto el **Frontend (UI)** como el **Backend (API)**. Está construido con las mejores prácticas de la industria, aplicando el patrón de diseño **Page Object Model (POM)** y **Behavior-Driven Development (BDD)**.

👉 **[Haz clic aquí para ver el Reporte de Pruebas Interactivo (Allure) en vivo](https://maalvrei.github.io/playwright-cucumber-java/)**

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 21
* **Herramienta principal:** Playwright (Web & API)
* **Framework BDD:** Cucumber (Alineado v7.15.0)
* **Datos Dinámicos:** Datafaker (Data-Driven Testing)
* **Reportería Visual:** Allure Report
* **CI/CD Pipeline:** GitHub Actions (Serverless)
* **Motor & Gestor:** JUnit 5 / Maven / Google Gson

## 📐 Arquitectura y Patrones de Diseño

El proyecto está estructurado para ser altamente escalable y mantenible:
* **Page Object Model (POM):** Separación estricta entre la lógica de las pruebas y los localizadores visuales (`pages`).
* **BDD (Gherkin):** Casos de prueba escritos en lenguaje natural (español) dentro de la carpeta `features`.
* **Step Definitions:** Conexión modular entre los pasos en Gherkin y las acciones de Playwright (`steps`).
* **Generación de Datos Dinámicos:** Integración de Datafaker para evitar el hardcodeo de datos en las peticiones API y evitar falsos positivos en BD.
* **Infraestructura Cloud Optimizada (CI/CD):** Integración lista mediante `.github/workflows/ci-pipeline.yml` para despliegues automatizados. Incluye estrategias avanzadas de DevOps como el uso de **Caché** para los binarios de Playwright (reduciendo los tiempos de ejecución) y **disparadores condicionales** (`paths-ignore`) para optimizar el consumo de recursos en la nube.

## 🧪 Tipos de Pruebas Implementadas

1. 🌐 **Pruebas UI (Frontend):** Navegación automatizada y validación de elementos en la web de pruebas *SauceDemo*.
2. ⚙️ **Pruebas API (Backend):** Peticiones HTTP (GET/POST) a *JSONPlaceholder* con inyección de cargas útiles (payloads) generadas de forma aleatoria.
3. 🧬 **Pruebas Híbridas (E2E Avanzado):** Extracción de datos reales desde la API (Parseo de JSON) e inyección de esos datos en la interfaz web durante la misma ejecución para realizar *Negative Testing*.

## 📁 Estructura del Proyecto

```text
.github/
 └── workflows/
      └── ci-pipeline.yml        # Pipeline de CI/CD (GitHub Actions)
src/
 ├── main/
 └── test/
      ├── java/com/automation/testing/
      │    ├── pages/            # Clases POM (Ej: LoginPage.java)
      │    ├── runners/          # Ejecutor de la suite y conexión con Allure
      │    └── steps/            # Definición de pasos BDD
      └── resources/
           ├── features/         # Archivos de pruebas en Gherkin (.feature)
           └── allure.properties # Configuración de reportería
```

## 💻 Cómo ejecutar el proyecto localmente (Para Evaluadores)

Para facilitar la revisión técnica, a continuación se detallan los pasos exactos para levantar y ejecutar la suite de pruebas desde cero. Las pruebas están configuradas para ejecutarse en modo `Headless` (invisible) para asegurar su compatibilidad con cualquier entorno y herramientas CI/CD.

### 🐧 Entorno Ubuntu / Linux (Limpio)
Si estás evaluando el proyecto desde una distribución de Linux limpia, ejecuta estos comandos en tu terminal:

1. Instala las herramientas base (Git, Java 21 y Maven):
   ```bash
   sudo apt update
   sudo apt install git maven openjdk-21-jdk -y
   ```
2. Clona el repositorio y entra al directorio:
   ```bash
   git clone [https://github.com/maalvrei/playwright-cucumber-java.git](https://github.com/maalvrei/playwright-cucumber-java.git)
   cd playwright-cucumber-java
   ```
3. Descarga los binarios de los navegadores de Playwright y sus dependencias del sistema operativo:
   ```bash
   mvn exec:java -e -D"exec.mainClass"="com.microsoft.playwright.CLI" -D"exec.args"="install --with-deps"
   ```
4. Ejecuta la suite de pruebas:
   ```bash
   mvn test
   ```

### 🪟 Entorno Windows
Para ejecutar el proyecto en Windows, necesitas tener instalados y configurados en tus variables de entorno (`PATH`): **Java 21**, **Git** y **Maven**.

> ⚠️ **Nota sobre Maven:** Si al intentar ejecutar los pasos de abajo obtienes el error `"mvn" no se reconoce como un comando interno o externo`, significa que no tienes Maven instalado a nivel global. Para solucionarlo: descárgalo desde [la web oficial de Apache Maven](https://maven.apache.org/download.cgi), descomprímelo en tu disco duro y añade la ruta de su carpeta `bin` a las variables de entorno de tu sistema (`PATH`).

Una vez tengas las herramientas listas, abre tu terminal (PowerShell o CMD) y sigue estos pasos:

1. Clona el repositorio y entra al directorio:
   ```bash
   git clone [https://github.com/maalvrei/playwright-cucumber-java.git](https://github.com/maalvrei/playwright-cucumber-java.git)
   cd playwright-cucumber-java
   ```
2. Instala los binarios de los navegadores de Playwright:
   ```bash
   mvn exec:java -e -D"exec.mainClass"="com.microsoft.playwright.CLI" -D"exec.args"="install"
   ```
3. Ejecuta las pruebas:
   ```bash
   mvn test
   ```

## 📊 Visualización de Resultados (Allure)

El proyecto cuenta con **Integración Continua (CI)**. Cada vez que se realiza un nuevo Push al repositorio, GitHub Actions ejecuta las pruebas automáticamente y publica los resultados.

* 🟢 **Ver resultados online:** [Reporte Allure en vivo](https://maalvrei.github.io/playwright-cucumber-java/)
* 💻 **Ver resultados en local:** Si ejecutas las pruebas en tu máquina con `mvn test`, puedes generar el reporte visual ejecutando `mvn allure:serve`.