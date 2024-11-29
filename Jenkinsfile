pipeline {
    agent any
    tools {
        maven 'maven-3.8.1'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/tu-usuario/tu-repositorio.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'sonar-scanner'
                }
            }
        }
    }
