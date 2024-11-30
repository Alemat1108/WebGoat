pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Descarga el código del repositorio
                git url: 'https://github.com/Alemat1108/WebGoat.git', branch: 'main'
            }
        }

        stage('Compile') {
            steps {
                script {
                    sh '''
                    # Limpia los archivos compilados anteriores
                    rm -rf out
                    mkdir out
                    
                    # Compila los archivos Java
                    find src -name "*.java" > sources.txt
                    javac -d out @sources.txt
                    '''
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'Escaneo' // Utiliza 'Escaneo' como el nombre del SonarQube Scanner
            }
            steps {
                // Ejecuta el análisis de SonarQube
                withSonarQubeEnv('Escaneo') {
                    sh "${scannerHome}/bin/sonar-scanner -Dproject.settings=sonar-scanner.properties"
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Espera a que el análisis de SonarQube se complete y verifica el estado de la puerta de calidad
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
