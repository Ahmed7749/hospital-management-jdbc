# hospital management jdbc (v1.0.0)
hey guys this is my first offical project using java and jdbc for a hospital management system. im currently a second year student so pls dont judge the code too hard lol. this is version 1.0.0.

it runs on console/terminal and i used mysql for the database.

 ## features
- add doctors and patietns
- book apointments for patients with doctors
- update names and stuff
- delete entries
- view all lists
- logic for major salaries (surgeon gets more lol)

### how to run
1. clone the repo
2. make sure u have mysql installed
3. run the sql files inside the database folder. i think you need to create the schema first using Scheme.sql then the tables.
4. IMPORTANT: set the env variables (see below) or it crashes.
5. run Main.java in src folder.


### setting up env variables (IntelliJ)
ok so the app will crash if u dont set these variables cause i didn't hardcode the creds (security practice or whatever).

if ur using IntelliJ:
1. go to the top right where the run button is
2. click the dropdown next to Main and select Edit Configurations...
3. in the menu look for "Environment variables" field
4. click the little icon on the right of that field and add these:

| Name | Value (example) |
| --- | --- |
| DB_URL | jdbc:mysql://localhost:3306/hospital_db |
| DB_USER | root |
| DB_PASSWORD | 1234 (or whatever ur mysql pass is) |

5. hit apply and ok. then run it.

TODO
- fix some bugs with dates
- I noticed some potionals to include GenericDAO to help with the reptitive code(i know it bothers you too)
- I think that making the Scanner a class attribute is wrong
- 100 percent gonna expand it more and add more functionality to it
- clean up the code in controllers

Diagrams
i added some images in docs folder for the class diagram and ER diagram.


## Purpose:
  This project i created it to strength my solid fundementals and understanding of native JDBC and MVC design pattern. I aim to enchance this project more and make it into Servlets API and JSP (i know it's old but it is for fundementals)
  I will then finally implement Spring frameowrk as a final version of the project.
  All this process is to build my strong base to be able to learn any tech stack that could be challenging in the future
  Thanks !
## IMPORTANT NOTE: 
 1- The project did not recive its full time due to the stress of univeristy exams.
 2- Sadly i wanted to refactor everything in the intial versoin but i thought it'd be a good idea to see how it improves (:
enjoy :D


___________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
# Version 2.0

### Critical Changes
  1. included the generic dao
  2. made only one scanner that is used in main instead of making a dependency like in version 1.0
  3. added some new functionality
  4. i cleaned the code in the cotrollers and i made it handle the I/O.
### Purpose of this version
  To improve my understanding of the SRP. I tried to refactor as much as i could so i can make this code maintainable
### Upcoming version
  version 3.0 will be a new repo that will be on web !. I will primly reuse what i have used in this project to follow the object reuse oriented approach !
