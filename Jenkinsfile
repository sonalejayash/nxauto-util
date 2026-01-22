pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        WORKSPACE_DIR = "C:\\temp\\nx-workspace"
        EXECUTION_ID  = "jenkins-run"
        JAR_NAME      = "nxauto-util-1.0.0-shaded.jar"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat """
                mvnw.cmd clean test package
                """
            }
        }

        stage('Prepare Runtime Workspace') {
            steps {
                bat """
                if not exist %WORKSPACE_DIR%\\nxutil\\input (
                    mkdir %WORKSPACE_DIR%\\nxutil\\input
                )

                echo ^<testcase id="ci"/^> > %WORKSPACE_DIR%\\nxutil\\input\\testcase_ci.xml
                """
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat """
                java -jar target\\%JAR_NAME% %WORKSPACE_DIR% %EXECUTION_ID%
                """
            }
        }
    }

    post {
        success {
            echo 'NX Auto Utility pipeline completed successfully'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'NX Auto Utility pipeline failed'
        }
    }
}
