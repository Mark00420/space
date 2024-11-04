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
        stage('Build') {
            steps {
                script {
                    docker.build("mark00420/frontend:latest", "./frontend")
                    docker.build("mark00420/backend:latest", "./backend")
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
