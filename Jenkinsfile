pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    environment {
        DB_USER = credentials('SPORTY_DB_USER')
        DB_PASSWORD = credentials('SPORTY_DB_PASSWORD')
        DB_NAME = credentials('SPORTY_DB_NAME')
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