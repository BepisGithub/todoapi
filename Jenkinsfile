// LET'S FIRST MAKE IT PUSH TO ECR, ONCE THAT WORKS WE CAN MAKE IT CREATE AN ECS SERVICE

pipeline {
    agent any

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
                    echo "AWS_REGION = ${env.AWS_REGION}"
                    echo "ECR_REGISTRY = ${env.ECR_REGISTRY}"
                    echo "ECR_REPOSITORY = ${env.ECR_REPOSITORY}"
                    echo "IMAGE_TAG = ${env.$IMAGE_TAG}"

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
