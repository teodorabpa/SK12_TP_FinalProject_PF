**Project Overview:**

This is a Java Maven test automation project based on TestNG and Selenium WebDriver technologies. 
The project is organized following the Page Object Model design pattern and Page Factory.
The project contains different test scenarios against the following website: http://training.skillo-bg.com:4200/posts/all as follows:

1. **"Home page test"** a quick test that verifies the home page url is loaded;
2. **"Login test"** verifies that an already registered user can log in the application successfully.
3. **"Unsuccessful login test with invalid credentials"** verifies that the user can not log in the application with invalid credentials.
4. **"Unsuccessful login test with missing credentials"** verifies that the user can not log in the application with invalid credentials.
5. **"Log-out test"** verifies that already logged user can log out the application successfully.
6. **"Register user"** verifies that an unregistered user can register in the application.
7. **"Unsuccessful registration"** verifies that the user can not register twice with same credentials.
8. **"Edit profile"** verifies that the user can edit their profile.
9. **"Create post"** verifies that an already logged user can crate a post. 
  
The project contains a testng.xml file.
All tests are runnable with the 'mvn clean test' command against the Chrome browser.
