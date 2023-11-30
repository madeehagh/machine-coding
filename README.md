## machine-coding (splitwise)

Main class splitwise.src.main.java.com.splitwise.MainApplication.java


### Functionalities:
1. Divide expense among users
2. If user does not exist, send invite
3. Register user
4. View balance of a user


#### H2-database

To login to the H2 database console, you can follow these steps:
1. Make sure your application is running locally on http://localhost:8080.
2. Open a web browser and navigate to http://localhost:8080/h2-console/.
3. On the H2 database login page, you will see a form to enter the login credentials.
4. In the "JDBC URL" field, enter the JDBC URL mentioned in your application.properties file. It should be something like jdbc:h2:mem:testdb.
5. In the "User Name" field, enter the username mentioned in your application.properties file. It is typically set to sa by default.
6. In the "Password" field, enter the password mentioned in your application.properties file. It is typically set to password by default.
7. Leave the "Driver Class" field as it is (org.h2.Driver).
8. Click on the "Connect" button to log in to the H2 database console.
![](/Users/administrator/IdeaProjects/splitwise-design/h2-login.png)

#### Run GithubActions locally
Install act on your system as mentioned in [https://github.com/nektos/act](https://github.com/nektos/act)
Install Docker [https://docs.docker.com/get-docker/](https://docs.docker.com/get-docker/)
Make sure docker is properly installed by running `docker version` command on your terminal
Once act and docker is installed, run `
act -j analyze --env GITHUB_TOKEN=YOUR_TOKEN`
from workflow root directory `/IdeaProjects/splitwise-design`

#### OpenAPI
APIs can be accessed on
http://localhost:8080/swagger-ui/index.html

### Non-Functional:
1. CodeQL to run static analysis on code

##### TODO:
Implement OAUTH authorisation
