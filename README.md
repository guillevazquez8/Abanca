# Abanca Project

## Index
* Set-up instructions
* API design and development

## Set-up instructions

1. To make it work, you need to have JDK in your computer. If you don't have it yet, you can download it in this link https://www.oracle.com/es/java/technologies/downloads/
2. In Java applications, all the libraries needed for the API to work correctly are in the pom.xml file. You have to execute this file to download everything inside the API.
3. Finally, you also need to configure the database. In Java, this is done in the __application.properties__ file, inside __src/main/resources/__. There, you must change the __spring.datasource__ info with the database you want to use (in my case MySQL, it can be any relational database you want). For stablishing a connection with it, Spring Data JPA will help. It'll be downloaded when you execute the pom.xml file. Next, I paste 2 screenshots to help understand this process.

   In the 1st picture, that's what you see when you click in the dog symbol (in my IDE it appears in the right side of the screen), and then in the plus symbol (up left), you need to click in DB connection.
   
   ![pic 1](https://github.com/user-attachments/assets/7d4ac855-5443-464d-97a4-a683bf22522c)

   In the 2nd picture, that's the configuration you need to add, and you can click in test connection to see if it's working. To work with MySQL you need to choose PostgreSQL in database type.
   
   ![pic 2](https://github.com/user-attachments/assets/5c06f9fa-cd89-4a44-b6a6-c4615b50a227)

When it establishes a proper connection, Hibernate itself will take care of creating the database based on the application model.

You will surely have some problems because Java applications can be somewhat complex to configure if you don't have experience, don't worry. To interact with the API, once you get it working, you can find documentation at the url http://localhost:8080/swagger-ui/

## API design and development

The tech stack used for the project is Java-Spring Boot, with MySQL as relational database. The API is organized in 4 layers: model (entities), repositories, services, and controllers. Apart from these layers, I've included a security folder, an exceptions folder, and testing. 

For the development of the program I've followed the next steps:

1. I've started with a class diagram in UML (you can find it below): there're 3 types of users (account holders, admins, and third parties), and 4 types of accounts (checking, student checking, savings, and credit card accounts), each one with specific attributes and properties.
2. Next, I've written and designed the tests that I wanted the API to be able to pass succesfully, trying to reflect on different possible user stories.
3. Following, I've created the different classes  with the necessary attributes, and established the relationships between them, for which the diagram helped a lot, as it's a clear and precise way of reflecting what's later going to be a complex coding structure. 
4. After that,
I've created the repositories with the necessary queries.
5. Later, I've developed the services with the business logic. I've included methods for creating the different accounts, and a transfer function which included the deduction and deposit method in the respective accounts. Other methods are included, such as the penalty fee and the interest rate method. 
6. At last I've made the controllers, which included several endpoints for the methods developed before. These endpoints were tested with a Postman collection. 
7. I've included an exception handler at API level, which throws HTTP errors 400, 401, 403, 404 and 500. I've also included spring security with JWT and roles, for authentication and authorization.

![AbancaProject](https://user-images.githubusercontent.com/104373456/200312489-3fc3ad20-8869-41c3-b312-98eed1be1acc.png)
