pipeline {
    agent any

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

        stage('Verify Build Output') {
            steps {
                bat '''
                echo ===== TARGET DIRECTORY =====
                dir target
                '''
            }
        }

        stage('Prepare Runtime Workspace') {
            steps {
                bat '''
                if not exist C:\\temp\\nx-workspace\\nxutil\\input (
                    mkdir C:\\temp\\nx-workspace\\nxutil\\input
                )
                echo <testcase id="ci"/> > C:\\temp\\nx-workspace\\nxutil\\input\\testcase_ci.xml
                '''
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat '''
                echo Running NX Auto Utility
                java -jar target\\nxauto-util-1.0.0.jar C:\\temp\\nx-workspace jenkins-run
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
    }
}
