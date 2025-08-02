pipeline {
    agent any
 stages {
        stage('sending code files') {
            steps {
                sh ''' scp -o StrictHostKeyChecking=no -r * azureuser@20.193.250.218:/home/azureuser/'''
            }
        }

        stage('sending template file') {
            steps {
                    sshagent(['kubernetes-p1']) {
                    sh '''scp -o StrictHostKeyChecking=no  -r templates azureuser@4.186.26.17:/home/azureuser/'''
               }
           }
        }
    }

}


