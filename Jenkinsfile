pipeline {
    agent any

    options {
        timestamps()
    }

    environment {
        WORKSPACE_ROOT = "C:\\temp\\nx-workspace"
        EXECUTION_ID  = "jenkins-run"
        NXUTIL_HOME   = "${env.WORKSPACE_ROOT}\\nxutil"
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
                if not exist ${NXUTIL_HOME}\\${EXECUTION_ID} (
                    mkdir ${NXUTIL_HOME}\\${EXECUTION_ID}
                )

                if not exist ${NXUTIL_HOME}\\${EXECUTION_ID}\\input (
                    mkdir ${NXUTIL_HOME}\\${EXECUTION_ID}\\input
                )

                echo ^<testcase id="jenkins"/^> > ${NXUTIL_HOME}\\${EXECUTION_ID}\\input\\testcase_ci.xml
                """
            }
        }

        stage('Run NX Auto Utility') {
            steps {
                bat """
                dir target
                java -jar target\\nxauto-util.jar ${WORKSPACE_ROOT} ${EXECUTION_ID}
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
