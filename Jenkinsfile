pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    environment {
        ENV = credentials('sporty-shoes')
    }
    stages {
        stage('Build') {
            steps {
                git url: 'https://github.com/dthill/spring-assessment.git', branch: 'main'
                sh "echo $ENV>>.env"
                sh "docker compose build"
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker compose up -d'
            }
        }
    }
}