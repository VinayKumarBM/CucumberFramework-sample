
## **Overview:**
Cucumber is one such open source tool, which supports behavior driven development. To be more precise, Cucumber can be defined as a testing framework, driven by plain English text. It serves as documentation, automated tests, and a development aid – all in one.

For Demo purpose all the test cases are created for [automationpractice.com](http://automationpractice.com/index.php) site.

## **Some of the key features of this framework:**

* It support reports like Cucumber, Extent & ReportBuilder reports.
* It support parallel execution of feature files.
* It generates execution log file.  
* It also supports PDF file validation.
* Can run test on Chrome, Firefox and Internet explorer browser from command line.
* Easy integration of CI/CD pipeline.
* Framework uses Page Object Design Pattern, hence there is clean separation between test code and page specific code such as locators and layout.


## **Required Setup :**

- [Java](https://www.guru99.com/install-java.html) should be installed and configured.
- [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/) should be installed and configured.
- Download the files from Git repository either as zip file OR using [Git](https://phoenixnap.com/kb/how-to-install-git-windows).

## **Running Test:**

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

    mvn test

Above command will run all the tests in the feature files on default Chrome browser.

If we need to run any specific scenario in the feature file on a particular browser, then run the below command by changing @tags and browser name.

    mvn test -Dcucumber.options="--tags @newAddress" -Dbrowser="firefox"
	

Once the execution completes reports will be generated in below folder structure.

1. **Extent Report:** */target/generated-reports/TestResults.html*
2. **ReportBuilder Report:** */target/generated-reports/detailed-report/index.html*
