pipeline {
    agent any
    environment {
        GIT_CREDENTIALS_ID = 'github-credentials'
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
        stage('Build and Push') {
            steps {
                script {
                    docker.build("your-dockerhub-username/frontend:latest", "./frontend")
                    docker.build("your-dockerhub-username/backend:latest", "./backend")
                    docker.push("your-dockerhub-username/frontend:latest")
                    docker.push("your-dockerhub-username/backend:latest")
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
