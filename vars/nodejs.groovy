
def call() {
  pipeline {
      agent {
          node {
              lable 'workstation'
             }
         }
     }
     options {
         ansicolor('xterm')

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
     stage ('checkmark sca') {
         step {
        sh
         }
     }

     post {
         always {
         clear ws ()
     }
    }
     }

