# Abanca Project

As final project of the bootcamp we've had to develop a RESTful API of a banking system following some basic guidelines. It was an individual project, therefore we couldn't get help from teachers nor from other students, and we had two weeks to make it.

The tech stack used for the project, and the one we've worked with during the bootcamp, is Java, MySQL for database management and Spring Boot for microservices creation. I've worked with good practices for easy readibility, coherence and understanding of the business logic. I've organized the API in 4 layers: model (entities), repositories, services and controllers. Apart from these layers, I've included a security folder, an exceptions folder, and testing. 

For the development of the program I've followed the next order:

1. I've started with a class diagram in UML (you can find it at the end of the text), which follows the structure indicated by the project guidelines: it should have 3 types of users (account holders, admins and third parties), and 4 types of accounts (checking, student checking, savings and credit card accounts), each one with specific attributes and properties. 
2. I've continued with the creation of the different classes  with the necessary attributes, and established the relationships between them, for which the diagram helped a lot, as it's a clear and precise way of reflecting what's later going to be a complex coding structure. 
3. After that, I've created the repositories with the necessary queries.
4. Later, I've developed the services with the business logic. I've included methods for creating the different accounts, and a transfer funciton which included the deduction and deposit method in the respective accounts. Other methods are included, such as the penalty fee and the interest rate method. 
5. At last I've made the controllers, which included several endpoints for the methods developed before. These endpoints were tested with a Postman collection. 
6. I've included an exception handler at API level, which throws HTTP errors 400, 401, 403, 404 and 500. I've also included spring security with JWT and roles, for authentication and authorization, and several integration tests of the key methods of the controllers.

![AbancaProject](https://user-images.githubusercontent.com/104373456/200312489-3fc3ad20-8869-41c3-b312-98eed1be1acc.png)
