# Session 1:  Meet a class , a string, a float &  a big big decimal

## Goals

1. java class topics (fields, scope, packages) 
2. basic syntax
3. collections
4. compile & run

## Class script

1. [STUDENT]verify that you have everyting installed 

        javac -version
        mvn -version  
        
2. [STUDENT]create a new project  
      
        cd session1
        mvn archetype:generate -DgroupId=com.nearsoft -DartifactId=academy -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

3. [STUDENT]review created files 

				➜  session1 git:(master) ✗ tree academy
				training
				├── pom.xml
				└── src
				    ├── main
				    │   └── java
				    │       └── com
				    │           └── nearsoft
				    │                   └── App.java
				    └── test
				        └── java
				            └── com
				                └── nearsoft
				                        └── AppTest.java

				11 directories, 3 files
				
4.[STUDENT] open the project on idea

5.[INSTRUCTOR] Explain this concepts
     
    - Maven
    - Class
    - Packages


6.[INSTRUCTOR] Explain the course app that manages

           - classes/workshops
           - attendees
           - budget
           - training requests

7.[STUDENT] Create the package com.nearsoft.domain

8.[STUDENT] Create the following classes under package domain
       
       Student (FirstName, LastName , email)
       Workshop (Title, ,group max/min size, StartDate )
     
9.[INSTRUCTOR] explain constructors

10.[STUDENT] create basic constructors 
       
       Student (FirstName, LastName , email)
       Workshop (Title,  group max/min size, StartDate )


11.[INSTRUCTOR] explain getters and setters

12.[STUDENT] create getters and setters

13.[INSTRUCTOR] explain

       - Collections API
       - Interfaces , List
       - ArrayList
       - HashMap

14.[STUDENT] Add  field Students  to the workshop class
       private List<Student> students = new ArrayList<Student>();

15.[INSTRUCTOR] explain junit & maven test run

16.[STUDENT] modify pom.xml junit version 

		    <dependency>
		      <groupId>junit</groupId>
		      <artifactId>junit</artifactId>
		      <version>4.11</version>
		      <scope>test</scope>
		    </dependency>

17.[STUDENT] write the basic system specs

   CHALLENGE!! 
     - Workshop Group Can Be Opened If Enrolled Students Are More Than Min

        com.nearsoft.domain.WorkshopTest#workshopGroupCanBeOpenedIfEnrolledStudentsAreMoreThanMin
        
        
        public boolean canBeOpened() {
          return students.size() >= getMinGroupSize();
        }
        
        
        public void enroll(Student student) throws Exception {
              students.add(student);
        }



18.[INSTRUCTOR ] explains throw and cath exceptions

   
19.[STUDENT] add the spect for 

        // Workshop Can Not Have More Than Max Students
  
        @Test
        public void workshopCanNotHaveMoreThanMaxStudents() {
        
            Workshop java = new Workshop("Java Super Advanced", 1, 1, new Date());
            java.enroll(new Student("John", "Doe", "foo@doe.com"));
            try {
                java.enroll(new Student("Lisa", "Unlucky", "lisa@unlucky.com"));
                fail();
            }catch(Exception e){
                assertTrue(e.getMessage().contains("WorkshopGroupIsFull"));
            }
        
        
        } 
        
        
        public void enroll(Student student) throws Exception {
            if (students.size() < getMaxGroupSize()){
                students.add(student);
            }else{
                throw new Exception("WorkshopGroupIsFull");
            }
        
        }


20.[Student] creates the  WorkshopGroupIsFullException
