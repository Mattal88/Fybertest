Web Automation Challenge Tests of Fyber

This repo contains the tests for
i) Top 250 page returns at least 1 movie, in the list.
ii) The previous should be true , for each of the sorting options
iii) The previous should also be true , When navigating to Western genre

The tests for the above are in the TestTopMoviesChart class as testTop250PageReturnsAtleast1Movie,
testSortingOptionsList, testNavigationWesternGenreMovies

TestMoviesChartBase is the base class for the above test class

The test class utilizes the page objects and the factory class present in the com.webdriver.pages package

Instructions to run the tests : Clone or fork the project and
have mozilla firefox browser installed and maven installed on your system.

cd into the base directory and run mvn package
All the tests would run for you, please refer the tests for detailed java docs explaining the test steps