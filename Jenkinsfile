pipeline {
    agent none
    stages {
        stage("build & SonarQube analysis") {
            agent any
            steps {
                // Configura el entorno de SonarQube. Asegúrate de que 'My SonarQube Server' sea correcto
                withSonarQubeEnv('sonarqube') {
                    // Ejecuta el comando Maven para construir y analizar el proyecto con SonarQube
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                // Establece un tiempo límite de espera de 1 hora para el Quality Gate
                timeout(time: 1, unit: 'HOURS') {
                    // Espera el resultado del Quality Gate y aborta la pipeline si no pasa
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
