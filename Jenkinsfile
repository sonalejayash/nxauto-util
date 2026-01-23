pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Prepare Workspace') {
            steps {
                bat '''
                if not exist C:\\temp\\nx-workspace\\nxutil\\input (
                    mkdir C:\\temp\\nx-workspace\\nxutil\\input
                )
                echo ^<testcase id="jenkins"/^> > C:\\temp\\nx-workspace\\nxutil\\input\\testcase_ci.xml
                '''
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat '''
                dir target
                java -jar target\\nxauto-util.jar C:\\temp\\nx-workspace jenkins-run
                '''
            }
        }
    }

    post {
        failure {
            echo 'NX Auto Utility pipeline failed'
        }
        success {
            echo 'NX Auto Utility pipeline SUCCESS'
        }
    }
}
