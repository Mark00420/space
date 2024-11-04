pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/your-repo.git'
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
