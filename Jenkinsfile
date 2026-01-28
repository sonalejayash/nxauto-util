pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java') {
            steps {
                bat '''
                echo JAVA_HOME=%JAVA_HOME%
                where java
                where javac
                java -version
                javac -version
                '''
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
                if not exist C:\\temp\\nx-workspace\\nxutil\\jenkins-run (
                    mkdir C:\\temp\\nx-workspace\\nxutil\\jenkins-run
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
