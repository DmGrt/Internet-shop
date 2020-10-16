# Online Tech Store

Online store with implemented basic functionality
Two user roles with the corresponding functionality were implemented.
User capabilities:
- register;
- log in/out;
- choosing the necessary product;
- shopping cart managing;
- placing orders;

Admin capabilities:
- managing users;
- view orders and manage it;
- manage store's assortment; 

## Technologies Used

 - Java 14
 - Maven 3.6.3
 - Maven Checkstyle Plugin
 - Apache Tomcat
 - Servlet API
 - Mysql Connector Java 8.0.21
 - JDBC
 - JSTL 1.2
 - JSP
 - Bootstrap

## Project structure

### Web part
 A set of pages that display the result of the application to the user
 
##### The following web filters have been implemented:

- Authentication filter - is responsible for admitting only 
  registered users to the system.

- Authorization filter - ensures that the user cannot access 
  information that does not correspond to his role.

### Business logic part 
##### DAO and Service layers were implemented here:

- The DAO layer is responsible for retrieving information 
  from the database and updating it.

- The Service layer is responsible for processing information and modifying it 
  in the process of user interaction with the application.

##### The following web filters have been implemented:

- Authentication filter - is responsible for admitting only 
  registered users to the system.

- Authorization filter - ensures that the user cannot access 
  information that does not correspond to his role.

## Project launching

To run the project on your local machine, clone this repository. 

Make sure you have configured Tomcat Server and set up the MySQL and some 
RDBMS on your machine. 

Perform the initialization of the data scheme (sample here -> init_db.sql). 

Set up your schema's password and user's login in the ConnectionUtil class. 

If you have performed all the steps correctly, and your server is active, 
the application will start.

Author : [DmGrt](https://github.com/DmGrt)