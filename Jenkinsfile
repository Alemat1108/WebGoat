pipeline {
    agent none
    stages {
        stage("Clonar el repositorio") {
            agent any
            steps {
                echo 'Clonando el repositorio de GitHub...'
                git branch: 'main', 
                    url: 'https://github.com/Alemat1108/WebGoat.git', 
                    credentialsId: 'github-credentials', 
                    shallow: true
            }
        }
        stage("Build & SonarQube analysis") {
            agent any
            steps {
                withSonarQubeEnv('SONARQUBE') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completada. Publicando resultados...'
        }
        failure {
            echo 'La pipeline falló. Revisa los logs para más detalles.'
        }
    }
}
