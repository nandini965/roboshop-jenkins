def call() {
    pipeline {
        agent {
            node {
                label 'workstation'
            }
        }
    }
    options {
        ansicolor(xterm)

    }
    stage 'code quality test' {
        step {
            sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host_url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123'
           // sh run code quality test
        }
    }

    stage 'checkmark sast' {
        step {
            sh 'sh run checkmark sast'
        }
    }
    stage 'checkmark sca' {
        step {
       sh 'run checkmark sast'
        }
    }

    post {
        always {
            clear ws ()
        }
    }
}


