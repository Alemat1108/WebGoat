pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('Compile') {
            steps {
                sh './mvnw clean compile'
            }
        }
        stage('Scan') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh './mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                }
            }
        }
    }
}




