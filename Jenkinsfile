pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages {
        stage('Compile') {
            steps {
                script {
                    sh '''
                    # Limpia los archivos compilados anteriores
                    rm -rf out
                    mkdir out
                    
                    # Compila los archivos Java
                    javac -d out src/main/java/**/*.java
                    '''
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    sh '''
                    # Ejecuta pruebas o valida el proyecto manualmente
                    java -cp out org.example.MainClass # Reemplaza MainClass por tu clase principal
                    '''
                }
            }
        }
        stage('Scan with SonarQube


