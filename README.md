# Spring boot refresh project #
​
## Backend technical task ##
​
Implement an app which provides http endpoints to save and retrieve employees. The app should be based on Spring, Hibernate and h2 as a database.
​
Each employee has a name, unique code, and salary. Some employees might have subordinates and these subordinates might have their own subordinates.
The app should be able to save a single employee or several, retrieve a single or several, and get a salary sum of all subordinates of a given employee.
Like we have employee Alex, with 2 subordinates Anna and Rein. Rein also has 2 subordinates, Michael and Kristin.
- Alex
- Anna (1000 eur)
- Rein (1000 eur)
  - Michael (1000 eur)
  - Kristin (1000 eur)
    ​
    So, the result of getting a sum of salaries by Alex should be 4000 euros.
    ​
    We will look at the details of the implementation, so it’s better to do less but with better quality.
    Ship the code on Git Hub.
    ​
## Notes ##
​
to package: "gradle package"
​
to run with H2 database profile : ". ./run.sh"
​
H2 console: http://localhost:8080/h2-console/  (sa/pass)
​
swagger http://localhost:8080/swagger-ui/index.html
​
## Configuration files ##
​
application.properties
server:
  port: 8080
​
```
​
#### Default profile: H2 db  ####
​
application.properties
​
```
spring:
  datasource:
    url: jdbc:h2:~/test
    driverClassName: org.h2.Driver
    username: sa
    password: pass
  h2.console.enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate.ddl-auto: update
```
​
http://localhost:8000/h2-console/  (user/password)
```
insert into employee (name, salary, unique_code) values ('Alex',  1000, '0001');
insert into employee (name, salary, unique_code, manager_id) values ('Anna', 1000, '0002', 1);
insert into employee (name, salary, unique_code, manager_id) values ('Rein', 1000, '0003',1);
insert into employee (name, salary, unique_code, manager_id) values ('Michael', 1000, '0004',3);
insert into employee (name, salary, unique_code, manager_id) values ('Kristin', 1000, '0005', 3);
insert into employee (name, salary, unique_code, manager_id) values ('test51', 100, '0006', 5);
insert into employee (name, salary, unique_code, manager_id) values ('test52', 100, '0007', 5);
insert into employee (name, salary, unique_code, manager_id) values ('test71', 100, '0008', 7);
insert into employee (name, salary, unique_code, manager_id) values ('test72', 100, '0009', 7);
```
​
#### REST ####
​
```
http://localhost:8080/swagger-ui/index.html#/
```
