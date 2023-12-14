 def call() {
    pipeline {

        agent {
            node {
                label 'workstation'
            }
        }

        options {
            ansiColor('xterm')
        }

        stages {
            stage('code compile') {
                steps {
                    sh 'maven compile'
                }
            }
        stage('code quality') {
            steps {

                sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygates.wait=true -Dsonar.java.binaries=./target'
                // sh run code quality test
            }
        }

        stage('Unit Test Cases') {
            steps {
                sh 'echo Unit tests'
                //sh 'npm test'
            }
        }

        stage('CheckMarx SAST Scan') {
            steps {
                sh 'echo Checkmarx Scan'
            }
        }

        stage('CheckMarx SCA Scan') {
            steps {
                sh 'echo Checkmarx SCA Scan'
            }
        }

    }
    post {
        always {
            cleanWs()
        }
    }
  }
 }