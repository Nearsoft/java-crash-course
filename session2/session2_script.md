# Session 2:  Ponle Achiote, mijo!!

## Goals
   
   - jhipster
   - generators
   - Workshop lists
   - Workshop detail (enrolled students)
     
## Class script

1. [INSTRUCTOR] presents jhipster

2. [STUDENT] installs jhipster and dependencies

        npm install -g yo
        npm install -g bower
        npm install -g grunt-cli
        npm install -g generator-jhipster

3. [STUDENT] generate an application

        mkdir academy
        cd academy
        yo jhipster
          # base name? academy
          # java package? com.nearsoft.academy
        mvn   # runs the app


4. [STUDENT] navigate the app
  
    browse to http://localhost:8080  
    
5. [STUDENT] generate the main academy entities

        yo jhipster:entity student
        yo jhipster:entity workshop
    
    
