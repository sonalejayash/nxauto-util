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
                if not exist C:\\temp\\nx-workspace\\nxutil\\jenkins-run (
                    mkdir C:\\temp\\nx-workspace\\nxutil\\jenkins-run
                )
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
                java -jar target\\nxauto-util-1.0.0.jar C:\\temp\\nx-workspace jenkins-run
                '''
            }
        }
    }

    post {
        success {
            echo 'NX Auto Utility pipeline SUCCESS'
        }
        failure {
            echo 'NX Auto Utility pipeline FAILED'
        }
    }
}
