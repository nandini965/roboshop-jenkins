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

                       //   sh 'sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.32.12:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygates.wait=true'
                       sh 'echo code quality test'
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
               stage('Release Application') {

                   when {
                       expression {
                           env.TAG_NAME ==~ ".*"
                       }
                   }
                   steps {
                       sh 'env'
                       sh 'curl -v -u admin:admin123 --upload-file pom.xml http://172.31.33.0:8081/repository/maven-releases/org/foo/1.0/foo-1.0.pom'
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