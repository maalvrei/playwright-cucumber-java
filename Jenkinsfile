pipeline {
    agent any

    tools {
        // Asume que en tu servidor Jenkins tienes configurado Maven con el nombre 'Maven3'
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Descargando el código desde GitHub...'
                checkout scm
            }
        }

        stage('Instalar Dependencias') {
            steps {
                echo 'Descargando librerías de Maven...'
                bat 'mvn clean install -DskipTests' // 'bat' es para Windows, en Linux sería 'sh'
            }
        }

        stage('Instalar Navegadores Playwright') {
            steps {
                echo 'Instalando los binarios de Playwright...'
                bat 'mvn exec:java -e -D"exec.mainClass"="com.microsoft.playwright.CLI" -D"exec.args"="install --with-deps"'
            }
        }

        stage('Ejecutar Pruebas E2E y API') {
            steps {
                echo 'Lanzando la suite de Cucumber...'
                // Ejecutamos las pruebas. Usamos catchError para que si un test falla, el pipeline siga y genere el reporte
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn test'
                }
            }
        }
    }

    post {
        always {
            echo 'Generando Reporte Allure...'
            // Requiere tener el plugin de Allure instalado en Jenkins
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]

            echo 'Limpiando el espacio de trabajo...'
            cleanWs()
        }
    }
}