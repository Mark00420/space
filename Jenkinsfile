pipeline {
    agent any
    environment {
        DOCKER_REGISTRY = 'your-docker-registry'
        DOCKER_IMAGE = 'your-image-name'
        KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-credentials'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Mark00420/space.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${DOCKER_IMAGE}:latest")
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        docker.image("${DOCKER_REGISTRY}/${DOCKER_IMAGE}:latest").push()
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    withCredentials([file(credentialsId: env.KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
                        sh '''
                        kubectl apply -f k8s-deployments/mysql-pv.yaml
                        kubectl apply -f k8s-deployments/mysql-deployment.yaml
                        kubectl apply -f k8s-deployments/backend-deployment.yaml
                        kubectl apply -f k8s-deployments/frontend-deployment.yaml
                        '''
                    }
                }
            }
        }
    }
}
