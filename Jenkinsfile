pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        // Le pedimos a Jenkins la ruta exacta de Maven para evitar que Linux se pierda
        MVN_HOME = tool('Maven3')
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
                // Usamos la ruta absoluta de Maven
                sh '"$MVN_HOME/bin/mvn" clean install -DskipTests'
            }
        }

        stage('Instalar Navegadores Playwright') {
            steps {
                echo 'Instalando los binarios de Playwright...'
                // Quitamos --with-deps porque el usuario de Jenkins en Docker no tiene permisos root
                sh '"$MVN_HOME/bin/mvn" exec:java -e -D"exec.mainClass"="com.microsoft.playwright.CLI" -D"exec.args"="install"'
            }
        }

        stage('Ejecutar Pruebas E2E y API') {
            steps {
                echo 'Lanzando la suite de Cucumber...'
                // Si la UI falla por falta de entorno gráfico en Linux, el pipeline sigue vivo
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    sh '"$MVN_HOME/bin/mvn" test'
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