

# To Do App
## This is a simple CRUD app for a To Do list.


Tabela de conteúdos
=================
<p align="center">
 <a href="#requirements">Requirements</a> •
 <a href="#running">Running the App</a> • 
 <a href="#techs">Technologies</a> • 
 <a href="#features">Features</a> 
</p>



### <p id="requirements">Requirements</p>
Before you start, make sure you have installed:
[Git](https://git-scm.com), [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and [Maven](https://maven.apache.org/)
Note that Maven requires JDK for compilation. Also, for this project I am using JDK 17



### <p id="running">Rodando o Back End</p>
```bash
# Clone this repo
 $ git clone https://github.com/Joaovitor1998/to-do

# Access the project's folder
 $ cd to-do

# Run the app using Maven
 $ mvn spring-boot:run

# The server will be available at port:8089 - access localhost:8089
# For Swagger, go to: localhost:8089/swagger-ui.html
```



### <p id="techs">🛠 Tecnologias</p>
As seguintes ferramentas foram usadas na construção do projeto:
- [Java](https://www.java.com/) - JDK 17
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [Hibernate](https://hibernate.org/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)



### <p id="features">Features</p>
- [x] Find all tasks
- [x] Find task by id
- [x] Find all tasks that is done
- [x] Find all tasks that is not done
- [x] Create a new task
- [x] Update a task
- [x] Delete a task

