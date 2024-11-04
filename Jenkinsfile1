pipeline {
    agent any
    environment {
        KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-credentials'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Mark00420/space.git'
                sh 'ls -al'  // To confirm files are in place
            }
        }
        stage('Build and Push') {
            steps {
                script {
                    dockerCompose = docker.build("your-docker-compose-service")
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        dockerCompose.push()
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
