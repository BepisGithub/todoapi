// LET'S FIRST MAKE IT PUSH TO ECR, ONCE THAT WORKS WE CAN MAKE IT CREATE AN ECS SERVICE

pipeline {
    agent any

    environment {
        // Define environment variables
        ECR_REGISTRY = '${env.ECS_REGISTRY}'
        ECR_REPOSITORY = '${env.ECR_REPOSITORY}'
        IMAGE_TAG = 'latest'
        ECS_CLUSTER = '${env.ECS_CLUSTER}'
        ECS_SERVICE_NAME = '${env.ECS_SERVICE_NAME}'
        AWS_REGION = '${env.AWS_REGION}'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/BepisGithub/todoapi', branch: 'main'
            }
        }

        stage('Build with Gradle') {
            steps {
                script {
                    // Assuming you have a wrapper script checked into your repository
                    sh './gradlew build -x test'
                }
            }
        }

        stage('Build Docker Image and Push to ECR') {
            steps {
                script {
                    // Login to AWS ECR
                    sh 'aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REGISTRY'
                    // Build the Docker image
                    sh 'docker build -t todoapi .'
                    // Tag the Docker image
                    sh 'docker tag todoapi:latest $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG'
                    // Push the image to AWS ECR
                    sh 'docker push $ECR_REGISTRY/$ECR_REPOSITORY:$IMAGE_TAG'
                }
            }
        }

//         stage('Deploy to ECS') {
//             steps {
//                 script {
//                     // Update ECS service to use the new image
//                     sh "aws ecs update-service --cluster ${ECS_CLUSTER} --service ${ECS_SERVICE_NAME} --force-new-deployment"
//                 }
//             }
//         }
    }
}
