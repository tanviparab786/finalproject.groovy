pipeline {
    agent any

 stages {
    stage('pulling code from repository'){
        steps{
               git branch: 'main', url: 'https://github.com/tanviparab786/finalproject.code.git'

        }
    }
        stage('sending code files') {
            steps {
                sh ''' scp -o StrictHostKeyChecking=no -r * azureuser@4.240.96.242:/home/azureuser/finalproject/'''
            }
        }

        stage('sending template file') {
            steps {
                    // sshagent(['kubernetes-p1']) {
                    sh '''scp -o StrictHostKeyChecking=no -i ~/.ssh/id_k8s -r templates azureuser@4.186.26.17:/home/azureuser/'''
            //    }
           }
       }

       stage('Build and push docker image'){
        steps{
            withCredentials([string(credentialsId: 'dockerimage', variable: 'docker password')]) {
                sh 'docker login -u  -p ${dockerimage}'
                sh 'docker image push tanvi2828/f1f8'
                // sh 'docker image push tanvi2828/$JOB_NAME:v1.$BUILD_ID'
                // sh 'docker image push tanvi2828/$JOB_NAME:latest'

                 
                }
            
            }
        } 
    }

}
    




