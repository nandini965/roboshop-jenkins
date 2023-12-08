pipeline {
  agent {
   node {
    label 'workstation'
   }
  }

  parameters {
    choice(name: 'env', choices('dev','prod') description: 'pick environment' )
 }

   options {
   ansicolor(xterm)
   }

   stage ('terraform-init') {
   step {
   sh 'terraform-init backend_config=env-dev state.tfvars'
   }
   }
   stage ('terraform-apply') {
   step {
   sh 'terraform-apply -auto-approve=env-dev main.tfvars'
   }
   }
   post {
   always {
   clear ws()
   }
   }