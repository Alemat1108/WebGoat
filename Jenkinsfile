pipeline {
    agent any

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
            environment {
                scannerHome = tool 'SonarQubeScanner'
            }
            steps {
                // Ejecuta el análisis de SonarQube
                withSonarQubeEnv('Escaneo') {
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=my_project -Dsonar.sources=src/main/java -Dsonar.host.url=http://your-sonarqube-url -Dsonar.login=tu-token-real"
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

