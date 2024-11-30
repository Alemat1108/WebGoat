pipeline {
    agent any

    tools {
        // Define the SonarQube scanner tool
        sonarQube 'Escaneo'
    }

    stages {
        stage('Checkout') {
            steps {
                // Descarga el código del repositorio
                git url: 'https://github.com/Alemat1108/WebGoat.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Compila el proyecto
                sh './mvnw clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Ejecuta el análisis de SonarQube
                withSonarQubeEnv('Escaneo') {
                    sh './mvnw sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Espera a que el análisis de SonarQube se complete y verifica el estado de la puerta de calidad
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
