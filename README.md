# Abanca Project

The tech stack used for the project is Java-Spring Boot, with MySQL as relational database. The API is organized in 4 layers: model (entities), repositories, services, and controllers. Apart from these layers, I've included a security folder, an exceptions folder, and testing. 

For the development of the program I've followed the next steps:

1. I've started with a class diagram in UML (you can find it below): there're 3 types of users (account holders, admins, and third parties), and 4 types of accounts (checking, student checking, savings, and credit card accounts), each one with specific attributes and properties.
2. Next, I've written and designed the tests that I wanted the API to be able to pass succesfully, trying to reflect on different possible user stories.
3. Following, I've created the different classes  with the necessary attributes, and established the relationships between them, for which the diagram helped a lot, as it's a clear and precise way of reflecting what's later going to be a complex coding structure. 
4. After that, I've created the repositories with the necessary queries.
5. Later, I've developed the services with the business logic. I've included methods for creating the different accounts, and a transfer function which included the deduction and deposit method in the respective accounts. Other methods are included, such as the penalty fee and the interest rate method. 
6. At last I've made the controllers, which included several endpoints for the methods developed before. These endpoints were tested with a Postman collection. 
7. I've included an exception handler at API level, which throws HTTP errors 400, 401, 403, 404 and 500. I've also included spring security with JWT and roles, for authentication and authorization.

![AbancaProject](https://user-images.githubusercontent.com/104373456/200312489-3fc3ad20-8869-41c3-b312-98eed1be1acc.png)
