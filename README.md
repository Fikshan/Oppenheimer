# Automation Framework 

The approach to framework:
### The approach to framework:
- The framework is designed with TestNG,POM and POJO approach which utilzes Feature files to define scenarios
- The scenarios are defined using testNG test
- RestAssured library is used for executing the API requests
- Schema for response is defined using POJO classes
- Validations are done with deserialization and Hamcrest matchers
- Selenium library is used for executing the UI testing
- Page Object model design pattern is used for UI automation
- The reporting is achieved using Extent Reports with additional PDF generation capability and configurable path 
- Required dependencies are managed with help of Maven



### Issues discovered:
-  User story2- AC3 unable to uplaod CSV
   - if we upload valid CSV we are getting error message
   - if we upload blank CSV we are getting sucess message but data in DB records are not presnt

- User story3 
   -we are unable to downlaod  csv file after we login for the 2nd time -> Generate Tax Button is disabled

### Future enhancements:
- Framework integration with CI/CD pipeline
- Data Driving the script 
- Including screenshot of UI tests when failed



### Screenshots:

![image](https://github.com/Fikshan/Oppenheimer/assets/20777467/52dd87f7-72d2-44ba-b626-806df8674a80)
