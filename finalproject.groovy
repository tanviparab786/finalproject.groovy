pipeline{
    agent any
    stages{
        stage('pulling code from repository'){
            steps{
                git branch: 'main', url: 'https://github.com/tanviparab786/finalproject.code.git'
            }
        }
    }
}