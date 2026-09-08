pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building RideVault...'
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running RideVault tests...'
                bat 'mvnw.cmd test'
            }
        }

        stage('Package') {
            steps {
                echo 'RideVault package created.'

                archiveArtifacts artifacts: 'target/*.jar',
                                   fingerprint: true
            }
        }
    }

    post {

        success {
            echo 'RIDEVAULT CI PIPELINE SUCCESSFUL!'
        }

        failure {
            echo 'RIDEVAULT CI PIPELINE FAILED!'
        }
    }
}