# Session 2:  Ponle Achiote, mijo!!

## Goals
   
   - jhipster
   - generators
   - Workshop lists
   - Workshop detail (enrolled students)
     
## Class script

1. [INSTRUCTOR]  explains modern java web dev    
    - j2ee, tomcat
    - spring boot
    
2. [STUDENT] copies the session1 academy project     
    https://raw.githubusercontent.com/rilopez/java-crash-course/master/session2/completed/academy/pom.xml

3. [STUDENT]  create the spring boot app
        
        package com.nearsoft;
        
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        
        
        @SpringBootApplication
        public class App
        {
            public static void main( String[] args )
            {
                SpringApplication.run(App.class, args);
            }
        }

4. [STUDENT]  create the workshop controller

        @Controller
        public class WorkshopController {
            @RequestMapping("/workshops")
            public String list(Model model) {
        
                List<Workshop> workshops = new ArrayList<>();
                workshops.add(new Workshop("Java", 1, 10, new Date()));
                workshops.add(new Workshop("C#", 1, 10, new Date()));
                workshops.add(new Workshop("PHP", 1, 10, new Date()));
        
        
                model.addAttribute("workshops", workshops);
                return "workshops";
            }
        
        }

7. [STUDENT] turn off template cache

        # application.properties
        spring.thymeleaf.cache=false

7. [INSTRUCTOR] presents jhipster

8. [STUDENT] installs jhipster and dependencies

        npm install -g yo
        npm install -g bower
        npm install -g grunt-cli
        npm install -g generator-jhipster

9. [STUDENT] generate an application

        mkdir academy
        cd academy
        yo jhipster
          # base name? academy
          # java package? com.nearsoft.academy
        mvn   # runs the app


10. [STUDENT] navigate the app
  
    browse to http://localhost:8080  
    
11. [STUDENT] generate the main academy entities

        yo jhipster:entity student
        yo jhipster:entity workshop
    
    
