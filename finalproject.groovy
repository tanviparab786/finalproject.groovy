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
                    // sshagent(['kubernetes-project']) {
                    sh '''scp -o StrictHostKeyChecking=no -i ~/.ssh/id_k8s -r templates azureuser@4.186.26.17:/home/azureuser/'''
            //    }
           }
       }

       stage('Build and push docker image'){

        steps{
               sshagent(['docker1']) {
              
            withCredentials([string(credentialsId: 'dockerimage', variable: 'docker password')]) {
                sh ''' ssh -o StrictHostKeyChecking=no azureuser@4.240.96.242 <<EOF echo "$docker password" | docker login -u "tanvi2828" --password-stdin EOF '''
                sh '''ssh -o StrictHostKeyChecking=no azureuser@4.240.96.242 "docker image push tanvi2828/f1f8"'''                                                        
                 //    sh docker image push tanvi2828/$JOB_NAME:v1.$BUILD_ID
                //   sh docker image push tanvi2828/$JOB_NAME:latest
                }
            }
        } 
    }

}
}
    




