pipeline {
    agent none
    stages {
        stage("Clonar el repositorio") {
            agent any
            environment {
                GIT_TIMEOUT = '60'
            }
            steps {
                echo 'Clonando el repositorio de GitHub...'
                git branch: 'main', url: 'https://github.com/Alemat1108/WebGoat.git', credentialsId: 'github-credentials'
            }
        }
        stage("build & SonarQube analysis") {
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

