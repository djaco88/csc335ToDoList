# csc335ToDoList
Authors: Jacob Davis, Joseph Pettit, John Gumm, Quinn James

Demo Link (No Sound): https://www.youtube.com/watch?v=txThV-a-Cbo

To Run: open and run main.java in csc335ToDoList/src/main/java/todo.test.demo

Purpose: 

a program with a user friendly GUI that keeps track of tasks. Tasks can be added, changed, marked completed, assigned dates and times, and deleted. New tabs with different tasks can be added. Everything can be saved and loaded. There 9 different color themes that can be added to individual tabs for a better user experience.

-------------------------------------------------------------------------------------------------------------------------------------------------

GUI Details:

Exit GUI- either click the x on the top corner of the GUI or click Close in the File dropdown menu

Save- click save in the File dropdown menu

Load- click load in the File dropdown menu

Create New Tab- click on the Tabs dropdown menu and click Add Tab

Change Name of Tab- select the Rename Tab option in the Tabs dropdown menu

Change Color Theme- navigate to Change Tab Theme in the Colors dropdown menu (note: this theme is only applied to the current tab)

Get Help- selecting the About option in the Help dropdown menu will redirect you to this README file on github

Creating a Task- navigate to the desired tab you want to store the task in. Click New Task if you are currently viewing an existing task, and fill in the Task Title, a description (optional), select the date (optional), select the time (optional)

Completing a Task- check the Completed box with the desired task opened OR right click on the task in the tab's task list and select completed

Remove task- right click on the task in the tab's task list and select Remove Task

Remove Tab- click the x jsut above the tab name

-------------------------------------------------------------------------------------------------------------------------------------------------

Learning Experience: 

Working with a larger group on this project was in many ways easier than the previous two-person projects. Many of the problems from previous projects weren't an issue for us. We were able to all contribute code with various IDEs and from computers using different OS. It definitely felt like we learned a lot about coding with others in the projects leading to this one. We did still encounter some git merging errors in the begining since everyone had their own branch that they were working on. The main branch was sometimes days behind the rest, but we got that sorted in the first week or so. The project coordination was made easier with Trello, but really constant communication via discord was key to our success. The project itself was a bit simpler than some of the previous ones, so we were able to focus on a lot of the details that make our project look and feel finished. Our project runs smoothly and we were able to fix all of the bugs we could find that might affect the user experience. Using JavaFX we were able to create CSS files to add color and depth to our display. We feel confident with our final result and hope you enjoy it!

-------------------------------------------------------------------------------------------------------------------------------------------------

MVC Design Pattern: 
 
Model: 
  - SaveData.java - Stores application state in ArrayList of TabData objects.
  - TabData.java  - Stores tab name, theme, and ArrayList of TaskData objects. 
  - TaskData.java - Stores task title, description, date, time, and completed status. 

  - TodoTask.java - Stores task data displayed in GUI. 
  
View: 
  - Todo-Test.fxml   - View for MenuBar and TabPane. 
  - TabTemplate.fxml - View for Tab(s) housed in Todo-Test TabPane.
  - *Theme.css       - Defines color properties for all JavaFX components in Tab. 
  
Controller: 
  - ToDoController.java        - Controller for Todo-Test.fxml
  - TabTemplateController.java - Controller for TabTemplate.fxml
  
