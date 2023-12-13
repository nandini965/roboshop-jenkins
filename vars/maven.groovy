def call() {
    pipeline {
        agent {
            node {
                label 'workstation'
            }
        }
    }
    options {
        ansicolor('xterm')

    }
    stage ('code compile test') {
        step {
            sh 'mvn compile'
        }
    }
    stage ('code quality test') {
        step {
            sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host_url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.java.binaries=./target'

          //  sh run code quality test
        }
    }

    stage ('checkmark sast') {
        step {
            sh'run checkmark sast'
        }
    }
    stage 'checkmark sca' {
        step {
            sh 'run checkmark sca'

        }
    }

    post {
        always {
            clear ws ()
        }
    }
}
