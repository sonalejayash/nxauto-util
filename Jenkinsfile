pipeline {
    agent any

    environment {
        // Explicitly bind JAVA_HOME for Jenkins runtime safety
        JAVA_HOME = 'C:\\Program Files\\AdoptOpenJDK\\java17\\jdkx64'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java') {
            steps {
                bat '''
                echo === JAVA VERIFICATION ===
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
                bat '''
                echo === MAVEN BUILD ===
                mvnw.cmd clean package
                '''
            }
        }

        stage('Prepare Workspace') {
            steps {
                bat '''
                echo === PREPARE NX WORKSPACE ===
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
                echo === RUN NX AUTO UTILITY ===
                dir target
                java -jar target\\nxauto-util-1.0.0.jar C:\\temp\\nx-workspace jenkins-run
                '''
            }
        }
    }

    post {
        success {
            echo '✅ NX Auto Utility pipeline SUCCESS'
        }
        failure {
            echo '❌ NX Auto Utility pipeline FAILED'
        }
        always {
            echo 'Pipeline execution finished'
        }
    }
}
