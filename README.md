# machine-coding (splitwise)

Main class splitwise.src.main.java.com.splitwise.MainApplication.java


### Functionalities:
1. Divide expense among users
2. If user does not exist, send invite
3. Register user
4. View balance of a user


#### H2-database

1. To login to the H2 database console, you can follow these steps:
2. Make sure your application is running locally on http://localhost:8080.
3. Open a web browser and navigate to http://localhost:8080/h2-console/.
4. On the H2 database login page, you will see a form to enter the login credentials.
5. In the "JDBC URL" field, enter the JDBC URL mentioned in your application.properties file. It should be something like jdbc:h2:mem:testdb.
6. In the "User Name" field, enter the username mentioned in your application.properties file. It is typically set to sa by default.
7. In the "Password" field, enter the password mentioned in your application.properties file. It is typically set to password by default.
8. Leave the "Driver Class" field as it is (org.h2.Driver).
9. Click on the "Connect" button to log in to the H2 database console.

### Non-Functional:
1. CodeQL to run static analysis on code

##### TODO:
Implement OAUTH authorisation
