pipeline {
    agent any 
    
    stages { 
        stage('SCM Checkout') {
            steps{
           git branch: 'main', url: 'https://github.com/Alemat1108/WebGoat.git'
            }
        }
        // run sonarqube test
        stage('Run Sonarqube') {
            environment {
                scannerHome = tool 'Escaneo';
            }
            steps {
              withSonarQubeEnv(credentialsId: 'sonarqube-token', installationName: 'sonarqube') {
                sh "${scannerHome}/bin/sonar-scanner"
              }
            }
        }
    }
}
