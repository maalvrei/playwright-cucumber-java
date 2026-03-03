pipeline {
    agent any

    tools {
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
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Instalar Navegadores Playwright') {
            steps {
                echo 'Instalando los binarios de Playwright...'
                sh 'mvn exec:java -e -D"exec.mainClass"="com.microsoft.playwright.CLI" -D"exec.args"="install --with-deps"'
            }
        }

        stage('Ejecutar Pruebas E2E y API') {
            steps {
                echo 'Lanzando la suite de Cucumber...'
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    sh 'mvn test'
                }
            }
        }
    }

    post {
        always {
            echo 'Generando Reporte Allure...'
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]

            echo 'Limpiando el espacio de trabajo...'
            cleanWs()
        }
    }
}