# About

Application allowing users to access information about every public repository (without forks) for given owner. Private repositories are not included.

Technologies:
- Java 22
- Spring Boot 3.3.2

# Local environment setup

To start the server:
1. In command line navigate to your project directory
2. Run command ```./mvnw spring-boot:run```

# Usage

After starting the application, type ```http://localhost:8080/{username}``` in browser or web testing application, e.g. Postman, where *{username}* stands for GitHub username. After sending this request the information about public repositories owned by this user will be shown. Response includes repository name, owner and name with last commit SHA value for every branch in the repository. If user does not exist, application throws a 404 error with relevant message.
