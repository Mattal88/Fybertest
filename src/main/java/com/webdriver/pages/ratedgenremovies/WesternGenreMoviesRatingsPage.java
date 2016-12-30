package com.webdriver.pages.ratedgenremovies;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object class representing the page attributes and services offered by the western genre movies listing page
 * This page implements the RatedGenreMoviesPage interface as required in this framework to be accessed by the
 * RatedGenreMoviesPageFactory during the page object initialization to be used in the tests
 */
public class WesternGenreMoviesRatingsPage implements RatedGenreMoviesPage {
    //The Locator Constants, In a good design standard these would be stored in a separate config or properties file
    private final String WESTERN_GENRE_HEADER_IDENTIFIER = "Highest Rated Western Feature Films With At Least 25000 Votes";
    private final String SORT_BY_IDENTIFIER = "sorting";


    private WebDriver driver;

    //Page elements using page factory
    @FindBy(className = MOVIES_LIST_IDENTIFIER)
    private WebElement moviesList;
    @FindBy(className = SORT_BY_IDENTIFIER)
    private WebElement sortByList;

    //Wait element for this page
    private WebDriverWait westernGenreMoviesPageWait;

    public WesternGenreMoviesRatingsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        westernGenreMoviesPageWait = new WebDriverWait(driver,GENRE_MOVIES_PAGE_OBJECT_TIME_OUT_PERIOD,GENRE_MOVIES_PAGE_OBJECT_POLL_PERIOD);
    }

    /**
     * Method to return whether the current page has the heading identifier for the Western genre movies page
     * @return
     */
    public boolean isGivenGenreMoviesPage(){
        return (driver.findElement(By.tagName("h1")).getText()
                .equalsIgnoreCase(WESTERN_GENRE_HEADER_IDENTIFIER));
    }

    /**
     * Method to get the list of movies
     * @return
     */
    public List<WebElement> getMoviesList(){
        westernGenreMoviesPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(MOVIES_LIST_IDENTIFIER)));
        return moviesList.findElements(By.cssSelector(".lister-item.mode-advanced"));
    }

    /**
     * Method to get the name of the movie at a particular rank in the list
     * @param rank
     * @return
     */
    public String getRankedMovie(int rank){
        return getMoviesList().get(rank).findElement(By.cssSelector(".lister-item-content"))
                .findElement(By.cssSelector(".lister-item-header")).findElement(By.tagName("a")).getText();
    }

    /**
     * Method to get a list of sort options
     * @return
     */
    public List<String> getSortOptions(){
        westernGenreMoviesPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(SORT_BY_IDENTIFIER)));
        List<WebElement> sortByElements = sortByList.findElements(By.tagName("a"));
        List<String> sortByOptions = new ArrayList<String>();
        for(WebElement element:sortByElements){
            sortByOptions.add(element.getText());
        }
        return sortByOptions;
    }

    /**
     * Method to select a given sort option
     * @param option
     * @return
     */
    public RatedGenreMoviesPage selectSortItem(String option){
        sortByList.findElement(By.linkText(option)).click();
        return this;
    }

    /**
     * Method to get the current sort option
     * @return
     */
    public String getCurrentSortByOption(){
        List<WebElement> sortByElements = sortByList.findElements(By.tagName("a"));
        for(WebElement webElement: sortByElements){
            if(webElement.findElements(By.tagName("strong")).size()==1){
                System.out.println(webElement.getText());
                return webElement.getText();
            }
        }
        Assert.fail("Web Page sort options in illegal state");
        return null;
    }
}
