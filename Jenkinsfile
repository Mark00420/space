pipeline {
    agent any
    environment {
        GIT_CREDENTIALS_ID = 'github-credentials'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'main']], userRemoteConfigs: [[url: 'https://github.com/Mark00420/space.git', credentialsId: env.GIT_CREDENTIALS_ID]]])
            }
        }
        stage('Build and Deploy') {
            steps {
                script {
                    // Pull images and run containers locally
                    sh '''
                    docker-compose down
                    docker-compose build
                    docker-compose up -d
                    '''
                }
            }
        }
    }
}
