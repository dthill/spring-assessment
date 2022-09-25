pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    environment {
        DB_PASSWORD = credentials('SPORTY_DB_PASSWORD')
    }
    stages {
        stage('Build') {
            steps {
                git url: 'https://github.com/dthill/spring-assessment.git', branch: 'main'
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