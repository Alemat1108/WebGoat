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
        stage('Scan with SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '''
                    sonar-scanner \
                        -Dsonar.projectKey=my_project \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.host.url=http://172.25.93.19:9000 \
                        -Dsonar.login=sonarqube
                    '''
                }
            }
        }
    }
}


