# Role Based Access Control - User Management Microservice


hello world how are you

#### Overview
>The need to manage a user base for an online system is very frequent.
Goal of this project is to offer a generic user's data management microservice.

This microservice can offer a good and solid starting point for managing your accounts.
Thanks to this Role Based Access Control implementation it's easy to define roles and permissions for your specific application/prototype and subsequently apply these access rules on the users.

This project lends itself very well to implement new prototypes or to create new solutions based on microservice architecture.

The solution is thought using the Docker technologies with two different containers:
* one Spring Boot REST Apis
* one MySql 8.0 database

The code has been well tested (> 115 tests) using JUnit and Mockito, H2 in memory database and some standard libraries for the integration tests.


If you're using this software or a part of it, please support this open source with a small donation here:

<a href="https://www.buymeacoffee.com/andreag" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>

I'm not working at the moment... Thanks!

#### Exposed REST apis
Here below the most relevant features exposed using REST Apis:

#### User management features

* Register a new us
#### RBAC features: manages roles and permissions

* Retrieve all the permissions
* Retrieve the list of the existing roles
* Create a new role
* Retrieve a single role
* Delete a role
* Add a permission on a role
* Remove a permission on a role
* Create a new permission
* Update an existing permission (also enabled or disable it)
* Retrieve single permission
* Delete not used permission
* Api to generate a salt random value to encrypt password (configuration)

## REST apis exposed
Using a browser it's possible

kjhsdukby
g,krgunucj
toj;v u


rv lnriuvj  j jt4rjtjainer:

    ./run.cmd

Open a browser and explore the REST apis:

http://localhost:8090/swagger-ui.html

The RBAC microservice should be up and running and connecting with the MySql instance with the demo data.
If you want to remove this demo data proceed to configure your system as in "Setup a ready and empty RBAC" section.

Everything should be up and running :)

### Setup without Docker
>You can also setup and work on this project without to consider to use Docker.
You will just launch the Spring Boot application targeting the MySql database (on localhost or on a remote one).

Install Java 8 JDK.

Set up your MySql instance and create the empty database "users":

    CREATE DATABASE IF NOT EXISTS users;

Create and grant a new MySql user on the "users" database.

Open the application.properties file located in /src/main/resources.

Target your localhost MySql database:

    spring.datasource.url=jdbc:mysql://localhost:3306/users?useSSL=false&allowPublicKeyRetrieval=true

Set the username and password of the MySql's user:

    spring.datasource.username=yourMySqlUser
    spring.datasource.password=yourMySqlUserStrongPassword

Execute the microservice code using Maven:

    ./mvnw spring-boot:run
    
Open a browser and explore the REST apis:

http://localhost:8090/swagger-ui.html

The RBAC microservice should be up and running and connecting with the MySql instance with the demo data.
If you want to remove this demo data proceed to configure your system as in "Setup a ready and empty RBAC" section.

Everything should be up and running using your local MySql :)

### Setup a ready and empty RBAC
>You can prepare a specific clean RBAC microservice setup without any demo data and
> without to reset the database content at each restart.

Install Java 8 JDK.

Set up your MySql instance and create the empty database "users":

    CREATE DATABASE IF NOT EXISTS users;

Create and grant a new MySql user on the "users" database.

Open the application.properties file located in /src/main/resources.

Set the username and password of the MySql's user:

    spring.datasource.username=yourMySqlUser
    spring.datasource.password=yourMySqlUserStrongPassword

>For a production environment consider to create a specific MySql user for the database "users" using
 a strong password.

Disable the demo data setting as below:

    # enable initialization using schema.sql and data.sql
    spring.datasource.initialize=false
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.initialization-mode=never

Configure a different encryption salt:

    # password encryption
    microservice.security.salt=WZeBXmCI9cAz3LyY9Sdllj9l5iPsXC

>If you need, you can launch the microservice and using Swagger to call the rest endpoint /users/rbac/salt
> in order to generate a new random one.

Save the application.properties file.

Run the build of the microservice and lunch the container:

    ./run.cmd

The RBAC microservice should be up and running and connecting with the MySql instance without demo data.
Proceed with your RBAC configuration using the REST apis:

http://localhost:8090/swagger-ui.html

Restarting the db or the microservices should not affect the persistence of your RBAC configuration.

#### Security, encryption and decryption of sensible data
I've followed the blog post below in order to implement an encryption and decryption method for sensible data such
 as passwords.

http://www.appsdeveloperblog.com/encrypt-user-password-example-java/

#### Author
This project has been created in February 2020 by Andrea Giassi.

Andrea he's an italian Agile professional and Software Engineer actives in web systems and services.
Since 2002, Andrea is working in the IT market for several different companies and start ups and it has contributed
 to the success of several solutions and products.

About me:
https://www.linkedin.com/in/andreagiassi/


Please support this open source with a small donation here:

<a href="https://www.buymeacoffee.com/andreag" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>
