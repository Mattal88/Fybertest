package com.webdriver.pages;

import com.webdriver.pages.ratedgenremovies.RatedGenreMoviesPage;
import com.webdriver.pages.ratedgenremovies.RatedGenreMoviesPageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 *  Page Object for the Top Rated Movies Page
 */
public class TopRatedMovies {

    //The Locator Constants, In a good design standard these would be stored in a separate config or properties file
    public final String MOVIES_LIST_IDENTIFIER = ".//*[@id='main']/div/span/div/div/div[3]/table/tbody";
    private final String SORT_DROP_DOWN_IDENTIFIER = "lister-sort-by";
    private final String GENRES_LIST_IDENTIFIER = "quicklinks";

    //Timeout and poll periods for wait object , To be configured based on server and machine on which the tests run
    //These are default values large enough not to cause timeout exception , if not you need to raise a performance bug
    private static final int TOP_RATED_MOVIES_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    private static final int TOP_RATED_MOVIES_PAGE_OBJECT_POLL_PERIOD = 100;

    private WebDriver driver;

    //Page Object elements
    @FindBy(className = SORT_DROP_DOWN_IDENTIFIER)
    private WebElement sortDropDown;
    @FindBy(xpath = MOVIES_LIST_IDENTIFIER)
    private WebElement moviesList;
    @FindBy(className = GENRES_LIST_IDENTIFIER)
    private WebElement genresList;

    /* Custom wait object for this page , These could have been created as separate object to be shared by all the page
     * objects , But it is not a good design to do so , as each page's loading characteristics would differ and hence
     * its respective timeout and polling periods
     */
    private WebDriverWait moviesPageWait;

    public TopRatedMovies(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        moviesPageWait = new WebDriverWait(driver,TOP_RATED_MOVIES_PAGE_OBJECT_TIME_OUT_PERIOD,TOP_RATED_MOVIES_PAGE_OBJECT_POLL_PERIOD);
    }

    /**
     * Method returns the list of top 250 movies as displayed on the web page
     * @return
     */
    public List<WebElement> getTopMovies(){
        moviesPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MOVIES_LIST_IDENTIFIER)));
        return moviesList.findElements(By.tagName("tr"));
    }

    /**
     * Method returns the list of sort options available in the sort by select drop down widget
     * @return
     */
    public List<String> getSortOptions(){
        moviesPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(SORT_DROP_DOWN_IDENTIFIER)));
        Select sortDropDownSelect = new Select(sortDropDown);
        List<String> optionsList = new ArrayList<String>();
        List<WebElement> optionsFromSelect = sortDropDownSelect.getOptions();
        // There is a more convenient way by using java streams in java 8 ,
        // using for each loop so that you dont need to upgrade your compiler for running these tests
        for(WebElement option : optionsFromSelect){
            optionsList.add(option.getText());
        }
        return optionsList;
    }

    /**
     * Method selects a particular sort by option from the available sort by options in the drop down
     * @param sortOption
     * @return
     */
    public TopRatedMovies selectSortByOption(String sortOption){
        Select sortDropDownSelect = new Select(sortDropDown);
        sortDropDownSelect.selectByVisibleText(sortOption);
        return this;
    }

    /**
     * Method goes to the particular genre page as in the method parameters. by clicking on the list of elements in
     * the top rated movies by genre , if genre is given as "Western" then it clicks on Western
     * @param genre
     * @return
     */
    public RatedGenreMoviesPage goToGenreMoviesRatingsPage(String genre){
        List<WebElement> genres = getGenresList();
        for(WebElement webElement: genres){
            if(webElement.getText().equalsIgnoreCase(genre)) {
                webElement.findElement(By.tagName("a")).click();
                return new RatedGenreMoviesPageFactory().getRatedMoviesGenericPage(driver,genre);
            }
        }
        Assert.fail("No Such genre found");
        return null;
    }

    /**
     * Method returns the list of available genres to click on by the user in the page , That is the entries in the list
     * of Top Rated Movies by Genre
     * @return
     */
    public List<WebElement> getGenresList(){
        moviesPageWait.until(ExpectedConditions.elementToBeClickable(By.className(GENRES_LIST_IDENTIFIER)));
        return genresList.findElements(By.tagName("li"));
    }
}
