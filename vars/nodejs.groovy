
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
            stage('code quality') {
                steps {
                    //sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host_url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123'
                    sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123'
                    // sh run code quality test
                }
            }

            stage('unit test cases') {
                steps {
                    sh 'echo unit test'
                }
            }

            stage('checkmark sast scan') {
                steps {
                    sh 'echo checkmark sast scan'
                }
            }
            stage('checkmark sca scan') {
                steps {
                    sh 'echo checkmark sca scan'
                }
            }
        }

        post {
            always {
                clear ws()
            }
        }
    }
}
