
def call() {
    pipeline {
        agent {
            node {
                lable 'workstation'
            }
        }

        options {
            ansicolor('xterm')

        }
        stage('code quality test') {
            step {
                sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host_url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123'

                // sh run code quality test
            }
        }
        stage('unit testing') {
            step {
                sh 'echo unit test'
            }
        }
        stage('checkmark sast') {
            step {
                sh 'echo checkmark sast scan'
            }
        }
        stage('checkmark sca scan') {
            step {
                sh 'echo checkmark sca scan'
            }
        }

        post {
            always {
                clear ws()
            }
        }
    }
}
