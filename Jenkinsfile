pipeline {
    agent any
    environment {
        GIT_CREDENTIALS_ID = 'github-credentials'
        DOCKER_CREDENTIALS_ID = 'docker-credentials'
        KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-credentials'
    }
    triggers {
        githubPush()
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/Mark00420/space.git', credentialsId: env.GIT_CREDENTIALS_ID]]])
            }
        }
        stage('Build') {
            steps {
                script {
                    docker.build("your-dockerhub-username/frontend:latest", "./frontend")
                    docker.build("your-dockerhub-username/backend:latest", "./backend")
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    withDockerRegistry(credentialsId: env.DOCKER_CREDENTIALS_ID, url: 'https://index.docker.io/v1/') {
                        docker.image("your-dockerhub-username/frontend:latest").push()
                        docker.image("your-dockerhub-username/backend:latest").push()
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: env.KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
                        sh 'kubectl apply -f k8s-deployments/frontend-deployment.yaml'
                        sh 'kubectl apply -f k8s-deployments/frontend-service.yaml'
                        sh 'kubectl apply -f k8s-deployments/backend-deployment.yaml'
                        sh 'kubectl apply -f k8s-deployments/backend-service.yaml'
                        sh 'kubectl apply -f k8s-deployments/mysql-deployment.yaml'
                        sh 'kubectl apply -f k8s-deployments/mysql-service.yaml'
                    }
                }
            }
        }
    }
}
