pipeline {
    agent any
 stages {
        stage('sending code files') {
            steps {
                sh ''' scp -o StrictHostKeyChecking=no -r Dockerfile docker-compose.yml pom.xml src azureuser@20.193.250.218:/home/azureuser/'''
            }
        }

        stage('sending template file') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'kubernetes-p1', keyFileVariable: 'KEY')]) {
                    sh ''' scp -o StrictHostKeyChecking=no -i $KEY -r templates azureuser@4.186.26.17:/home/azureuser/'''
                }
            }
        }
    }
}
