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
            sh 'mvn compile test'
        }
    }
    stage ('code quality test') {
        step {
            sh run code quality test
        }
    }

    stage ('checkmark sast') {
        step {
            sh
        }
    }
    stage 'checkmark sca' {
        step {

        }
    }

    post {
        always {
            clear ws ()
        }
    }
}
