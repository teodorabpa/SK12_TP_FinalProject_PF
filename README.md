**Project Overview:**

This is a Java Maven test automation project based on TestNG and Selenium WebDriver technologies. 
The project is organized following the Page Object Model design pattern and Page Factory.
The project contains 8 different test scenarios against the following website: http://training.skillo-bg.com:4300/posts/all as follows:
1. "Home page test" verifies the home page url;
2. "Login test" verifies that an already registered user can log in the application successfully;
3. "Unsuccessful login test" verifies that the user can not log in the application with invalid credentials;
4. "Log-out test" verifies that the user can log out the application successfully;
5. "Register user" verifues that the user can register in the application  
  
The project contains a testng.xml file.
All tests are runnable with the 'mvn clean test' command against the Chrome browser.
