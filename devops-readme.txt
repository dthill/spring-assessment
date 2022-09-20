# Deploy Application on Cloud.

In this application the e-commerce spring-boot application which was developped during a previous module was deployed to an AWS EC2 instance.
The application was deployed using docker compose with one container running the application and a second running the MySql database. All secrets related to the project such as passwords and usernames for the databse were saved as environmental variables so that they can easily be switched and are not visible on GitHub.
The deployment was partially automated with a bash script which is responsible for installing docker and docker composer as well as start the containers.
The deployment could further be improved by using RDS as a database service and ECS in combination with fargate to simplify the deployment of the container. The use of AWS CloudFront would also provide an SSL/TLS setup enabling the use of https which is currently not implemented as it is beyond the scope of this project.

GitHub Repository:
https://github.com/dthill/spring-assessment
Deployed Application:
http://ec2-18-194-232-233.eu-central-1.compute.amazonaws.com/