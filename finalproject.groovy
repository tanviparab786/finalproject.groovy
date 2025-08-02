pipeline{
    agent any
    stages{
        stage('pulling code from repository'){
            steps{
                git branch: 'main', url: 'https://github.com/tanviparab786/finalproject.code.git'
            }
            }
                    stage('sending code files') {
                steps{
                     sh '''scp -o StrictHostKeyChecking=no -r * azureuser@20.193.250.218:/home/azureuser/'''
                 
                    }
                }
                    stage('sending template file') {
                       withCredentials([sshUserPrivateKey(credentialsId: 'kubernetes-p1', keyFileVariable: 'KEY')]) {
                        sh '''echo "Testing SSH connection..."
                            ssh -o StrictHostKeyChecking=no -i $KEY azureuser@4.186.26.17 "echo Connected successfully"
      
                              echo "Sending files..."
                             scp -o StrictHostKeyChecking=no -i $KEY -r templates azureuser@4.186.26.17:/home/azureuser/'''
                       }
                    }
                }  
                     
             }


        }
 
   }
