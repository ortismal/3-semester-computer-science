# Mandatory 2: Courses

The goal of this project is to create a web application for a school, where you can create courses, and assign teachers and students to these. 

## Getting Started

To download the program files, you will need to follow this link:

https://github.com/ortismal/3-semester-computer-science/

On the page to the top right you will see a green button saying 'clone or download'. Choose 'download ZIP', and when the folder is downloaded, extract the files to a location of your choice.
Once you have extracted the file, open up your IDE (we have used IntelliJ), and open the folder Mandatory2_FSS. Once the project is opened, you run the program and go to localhost:8080/courses

### Prerequisites

What you need to have to run this program:

1. IntelliJ IDEA (or any other IDE that supports Java and Spring).
2. MySQL Workbench.
3. A web browser.
4. Internet connection (because the database is on an online server)

## Test

To create a course:

1. Press the 'add new course' button on the top of the page.
2. Enter the correct data and press 'create'.
3. You will be able to see the course you created on the front page.

To create a student:

1. Press the student button to the right, on any of the courses.
2. You will see a list of students currently enrolled on the course.
3. Press the 'create student' button on the top.
4. Enter data for the student and press 'create'
5. You will be redirected to the courses page. Press the 'student' button again, and choose 'add student to course'. You will see the student you created on the list of students, and enroll him to the course.

To create a teacher:

1. Press the 'teacher' button to the right, on any of the courses.
2. You will see a list of teacher currently assigned on the course.
3. Press the 'create teacher' button on the top.
4. Enter data for the teacher and press 'create'
5. You will be redirected to the courses page. Press the 'teacher' button again, and choose 'add teacher to course'. You will see the teacher you created on the list of teachers, and assign him to the course.

## Built With

* [Spring](http://spring.io/projects/spring-framework) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

Patrick Sirich
Casper Frost Andersen
Christian Møller Strunge
