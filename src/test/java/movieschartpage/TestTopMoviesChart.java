package movieschartpage;

import com.webdriver.pages.TopRatedMovies;
import com.webdriver.pages.ratedgenremovies.RatedGenreMoviesPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * The Main Test class consisting tests for the Web Automation Challenge as mentioned in the PDF
 */
public class TestTopMoviesChart extends TestMoviesChartBase {


    /**
     * Test to check whether "The Top 250 (http://www.imdb.com/chart/top) page returns at least 1 movie​, in the list."
     * Steps: i) Get the list of movies from the Top 250 page by using the pageObject class
     * ii) Assert whether the size is greater than or equal to 1
     */
    @Test
    public void testTop250PageReturnsAtleast1Movie(){
        List<WebElement> movies = topRatedMovies.getTopMovies();
        Assert.assertTrue(movies.size() >=1,"Atleast One Movies Should be present in the list");
    }

    /**
     * Test to check whether there is atleast 1 movie in the list for each sorting option
     * Steps: i)Get the available sorting options from the page sort by drop down
     * ii) For each option , select the option in drop down and check whether atleast one movie is present in the list
     */
    @Test
    public void testSortingOptionsList(){
        //Get the available options from the dropdown
        List<String> optionsOfDropDown = topRatedMovies.getSortOptions();
        //For each option check whether atleast one movie is displayed in the list
        for(String option: optionsOfDropDown){
            topRatedMovies = new TopRatedMovies(driver);
            List<WebElement> movies = topRatedMovies.selectSortByOption(option).getTopMovies();
            Assert.assertTrue(movies.size() >=1,"Atleast One Movies Should be present in the list");
        }
    }

    /**
     * Test to check whether atleast one movie is present in the Western genre page when the user navigates to the
     * western genre page
     * Steps: i) Click on the "Western" in Top Rated movies by genre
     * ii) Check whether the appropriate page is displayed
     * iii)Check whether atleast one movie is present in the list
     * iv) Get all the sort by options available , remove the current sort option from the list
     * v) Test for each option sort for the movie list to have atleast one movie displayed
     */
    @Test
    public void testNavigationWesternGenreMovies(){
        RatedGenreMoviesPage westernGenreMoviesRatingsPage =topRatedMovies.goToGenreMoviesRatingsPage("Western");
        Assert.assertTrue(westernGenreMoviesRatingsPage.isGivenGenreMoviesPage());
        Assert.assertTrue(westernGenreMoviesRatingsPage.getMoviesList().size()>=1,"Atleast One Movies Should be present in the list");
        //Just to check which movie is displayed at a particular rank , here displaying the movie rank 20 in the list
        System.out.println(westernGenreMoviesRatingsPage.getRankedMovie(20));
        List<String> options = westernGenreMoviesRatingsPage.getSortOptions();
        //Remove the sort option which is already tested current test, this is to avoid the "^" or "v" symbol in the current sort
        options.remove(westernGenreMoviesRatingsPage.getCurrentSortByOption());
        //Test for each sort option for atleast one movie to be displayed in the list
        for(String option: options) {
            westernGenreMoviesRatingsPage.selectSortItem(option);
            Assert.assertTrue(westernGenreMoviesRatingsPage.getMoviesList().size()>=1,"Atleast One Movies Should be present in the list");
        }
    }
}
