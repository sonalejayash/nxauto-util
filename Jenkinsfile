pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    environment {
        JAVA_HOME = 'C:\\Program Files\\AdoptOpenJDK\\jdk-8-hotspot'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvnw.cmd clean test package'
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat '''
                mkdir C:\\temp\\nx-workspace\\nxutil\\input
                echo ^<testcase id="001"/^> > C:\\temp\\nx-workspace\\nxutil\\input\\testcase_ci.xml
                java -jar target\\nxauto-util-1.0.0.jar C:\\temp\\nx-workspace .
                '''
            }
        }
    }

    post {
        success {
            echo 'NX Auto Utility pipeline completed successfully'
        }
        failure {
            echo 'NX Auto Utility pipeline failed'
        }
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
