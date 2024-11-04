pipeline {
    agent any
    environment {
        DOCKER_CREDENTIALS_ID = 'docker-credentials'
        PATH = "${env.PATH};C:\\Program Files\\Docker\\Docker\\resources\\bin"
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/Mark00420/space.git', credentialsId: 'space']]])
            }
        }
        stage('Build') {
            steps {
                script {
                    docker.build("mark00420/frontend:latest", "./frontend")
                    docker.build("mark00420/backend:latest", "./backend")
                }
            }
        }
        stage('Deploy to Swarm') {
            steps {
                script {
                    bat 'docker stack deploy -c C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\space\\docker-compose.yml my-stack'
                }
            }
        }
    }
}
