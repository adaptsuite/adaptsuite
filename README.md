Adaptsuite
==============

Allows you to run different testing suites (fastest to slowest) of JUnit tests according to several heuristics such as execution time, historical number of failures, 
coverage last time the test ran and the frequency that the tool chooses the test.
The user can also set a degree of importance for which one of these parameters.

You may need the following libraries:
- jsoup-1.8.3.jar
- cpsuite-1.2.5.jar
- opencsv-3.5.jar

Before running this application, you must have a html Eclemma report within a "Cov" directory for each test of your program. Each .html must be named with the 
same name of the test class its belongs.
