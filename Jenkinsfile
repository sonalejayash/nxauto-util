pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        WORKSPACE_ROOT = "C:\\temp\\nx-workspace"
        EXECUTION_ID  = "jenkins-run"
        INPUT_DIR     = "${WORKSPACE_ROOT}\\nxutil\\${EXECUTION_ID}\\input"
    }

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
                bat """
                if not exist "${WORKSPACE_ROOT}\\nxutil\\${EXECUTION_ID}" mkdir "${WORKSPACE_ROOT}\\nxutil\\${EXECUTION_ID}"
                if not exist "${INPUT_DIR}" mkdir "${INPUT_DIR}"
                echo ^<testcase id="jenkins"/^> > "${INPUT_DIR}\\testcase_ci.xml"
                """
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat """
                dir target
                java -jar target\\nxauto-util.jar "${WORKSPACE_ROOT}" "${EXECUTION_ID}"
                """
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
