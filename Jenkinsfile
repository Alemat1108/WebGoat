pipeline {
    agent none
    stages {
        stage("Clonar el repositorio") {
            agent any
            steps {
                echo 'Clonando el repositorio de GitHub...'
                // Configuración de Git para manejar grandes volúmenes de datos y conexiones persistentes
                sh 'git config --global http.postBuffer 524288000'
                sh 'git config --global core.compression -1'
                sh 'git config --global http.postBuffer 157286400'
                sh 'git config --global http.maxRequestBuffer 100M'
                sh 'git config --global http.keepAlive true'
                sh 'git config --global http.maxKeepAliveRequests 100'
                retry(3) {
                    git branch: 'main', url: 'https://github.com/Alemat1108/WebGoat.git', credentialsId: 'github-credentials'
                }
            }
        }
        stage("build & SonarQube analysis") {
            agent any
            steps {
                withSonarQubeEnv('sonarqube') {
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
