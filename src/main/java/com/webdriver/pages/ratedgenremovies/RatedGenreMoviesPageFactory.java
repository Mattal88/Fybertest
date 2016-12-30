package com.webdriver.pages.ratedgenremovies;

import org.openqa.selenium.WebDriver;

/**
 * The factory class which initializes the appropriate page object as parameterised by genre
 * Here we can add various other genres , since we only need Western genre in our tests
 * adding only western genre initilizer
 */
public class RatedGenreMoviesPageFactory {

    public RatedGenreMoviesPage getRatedMoviesGenericPage(WebDriver driver,String genre){
        if(genre == null){
            return null;
        }
        // You can add other genres here
        if (genre.equalsIgnoreCase("Western")){
            return new WesternGenreMoviesRatingsPage(driver);
        }
        return null;
    }
}
