# About

Application allowing users to access information about every public repository (without forks) for given owner. Private repositories are not included.

# Local environment setup

To start the server:
1. In command line navigate to your project directory
2. Run command ```./mvnw spring-boot:run```

# Usage

After starting the application, type ```http://localhost:8080/{username}``` in browser or web testing application, e.g. Postman, where *{username}* stands for GitHub username. After sending this request the information about repositories owned by this user will be shown. Response includes repository name, owner and name with last commit SHA value for every branch in the repository.
For retrieving data you can also use Swagger UI, which runs under this link: ```http://localhost:8080/swagger-ui/index.html```
