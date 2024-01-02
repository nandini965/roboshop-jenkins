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
        environment {
            NEXUS = credentials('NEXUS')
        }

        stages {
            stage('code compile') {
                steps {
                    sh 'mvn compile'
                }
            }
            stage('code quality') {
                steps {

                    //   sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygates.wait=true -Dsonar.java.binaries=./target'
                    sh 'echo code quality'
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
            stage('Release application') {
            when {
                expression {
                    env.TAG_NAME ==~ ".*"
                }
            }
            steps {
                sh 'mvn package; cp target/${component}-1.0.jar ${component}.jar'
                sh 'echo $TAG_NAME >VERSION'
                sh 'zip -r ${component}-${TAG_NAME}.zip ${component}.jar VERSION'
                sh 'curl -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${component}-${TAG_NAME}.zip http://172.31.33.0:8081/repository/${component}/${component}-${TAG_NAME}.zip'
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