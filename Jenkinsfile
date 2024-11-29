pipeline {
    agent none
    stages {
        stage("build & SonarQube analysis") {
            agent any
            tools {
                maven 'Maven' // Nombre de la instalación de Maven configurada en Jenkins
                jdk 'JDK 17' // Usa el nombre del JDK configurado en Jenkins
            }
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Quality Gate") {
            agent any
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}


