package com.webdriver.pages.ratedgenremovies;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Interface to be implemented by all classes which represent the page objects for various genre movie ratings page
 */
public interface RatedGenreMoviesPage {
    public final String MOVIES_LIST_IDENTIFIER = "lister-list";

    //Timeout and poll periods for wait object , To be configured based on server and machine on which the tests run
    //These are default values large enough not to cause timeout exception , if not you need to raise a performance bug
    public static final int GENRE_MOVIES_PAGE_OBJECT_TIME_OUT_PERIOD = 5;
    public static final int GENRE_MOVIES_PAGE_OBJECT_POLL_PERIOD = 100;

    //For method definitions please check WesternGenreMoviesRatingsPage class which implements this interface
    public abstract boolean isGivenGenreMoviesPage();
    public abstract List<WebElement> getMoviesList();
    public abstract String getRankedMovie(int rank);
    public abstract List<String> getSortOptions();
    public abstract RatedGenreMoviesPage selectSortItem(String option);
    public abstract String getCurrentSortByOption();


}
