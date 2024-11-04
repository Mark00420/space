pipeline {
    agent any
    environment {
        DOCKER_CREDENTIALS_ID = 'docker-credentials'
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
        stage('Push') {
            steps {
                script {
                    withDockerRegistry(credentialsId: DOCKER_CREDENTIALS_ID, url: 'https://index.docker.io/v1/') {
                        docker.image("mark00420/frontend:latest").push()
                        docker.image("mark00420/backend:latest").push()
                    }
                }
            }
        }
        stage('Deploy to Swarm') {
            steps {
                script {
                    sh 'docker stack deploy -c docker-compose.yml my-stack'
                }
            }
        }
    }
}
