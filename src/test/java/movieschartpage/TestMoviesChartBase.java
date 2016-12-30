package movieschartpage;

import com.webdriver.pages.TopRatedMovies;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Base class which provides Test configuration , setup and tear down services for the sub classes
 */
public abstract class TestMoviesChartBase {

    protected final static String siteUrl = "http://www.imdb.com/chart/top";

    static WebDriver driver;

    //Base page object for the tests
    public  static TopRatedMovies topRatedMovies;

    /**
     * Before each test class run initializes and instantiates a webdriver instance
     */
    @BeforeClass
    public static void setupClass() {
        driver = new FirefoxDriver();
        Assert.assertNotNull(driver);
        driver.manage().window().setSize(new Dimension(1200, 850));
        driver.navigate().to(siteUrl);

    }

    @BeforeMethod
    public static void setupTest() {
        // Add custom setup if you still need some sanity check of environment before a test executes
        topRatedMovies = new TopRatedMovies(driver);
    }

    /**
     * After every test go to the base location , Top 250 Movies Page here
     */
    @AfterMethod
    public void tearDownTest() {
        //Go to the main screen at the end of test execution
        driver.navigate().to(siteUrl);
    }

    /**
     * After the tests in a test class complete thier run , log out of confluence and teardown the webdriver instance
     */
    @AfterClass
    public static void tearDownClass() {
        //Logout for the test
        driver.quit();
    }
}
