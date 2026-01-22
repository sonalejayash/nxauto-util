pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        WORKSPACE_DIR = "C:\\temp\\nx-workspace"
        INPUT_DIR     = "C:\\temp\\nx-workspace\\nxutil\\input"
        JAR_NAME      = "nxauto-util-1.0.0.jar"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvnw.cmd clean package'
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
                if not exist "%INPUT_DIR%" (
                    mkdir "%INPUT_DIR%"
                )

                echo ^<testcase id="ci"/^> > "%INPUT_DIR%\\testcase_ci.xml"
                '''
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat """
                echo Running NX Auto Utility...
                java -jar "target\\%JAR_NAME%" "%WORKSPACE_DIR%" jenkins-run
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
