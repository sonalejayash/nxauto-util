pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        RUNTIME_WORKSPACE = "C:\\temp\\nx-workspace"
        EXECUTION_ID      = "jenkins-run"
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
                if not exist %RUNTIME_WORKSPACE%\\nxutil\\input (
                    mkdir %RUNTIME_WORKSPACE%\\nxutil\\input
                )
                echo ^<testcase id="ci"/^> > %RUNTIME_WORKSPACE%\\nxutil\\input\\testcase_ci.xml
                '''
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat '''
                set JAR_PATH=%CD%\\target\\nxauto-util-1.0.0-shaded.jar
                echo Running JAR: %JAR_PATH%
                java -jar "%JAR_PATH%" %RUNTIME_WORKSPACE% %EXECUTION_ID%
                '''
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
