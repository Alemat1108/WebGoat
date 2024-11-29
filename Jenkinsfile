pipeline {
    agent any
    
    stages { 
        stage('SCM Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Alemat1108/WebGoat.git'
            }
        }
        stage('Run Sonarqube') {
            environment {
                scannerHome = tool 'Escaneo'
            }
            steps {
                withCredentials([string(credentialsId: 'sonarqube', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv('sonarqube') {
                        sh """
                            ${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=WebGoat \
                            -Dsonar.projectName=WebGoat \
                            -Dsonar.sources=src/main/java \
                            -Dsonar.host.url=http://172.25.93.19:9000 \
                            -Dsonar.login=${SONAR_TOKEN}
                        """
                    }
                }
            }
        }
    }
}
