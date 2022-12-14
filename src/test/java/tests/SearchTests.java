package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Tests for search")
public class SearchTests extends CoreTestCase {
    @Test
    @Feature("Search")
    @DisplayName("Search 'Java'")
    @Description("Type 'Java' in search line and wait for search result with 'Object-oriented' string in description")
    @Severity(SeverityLevel.NORMAL)
    public void testSearchJava()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented");
    }
    @Test
    @Feature("Search")
    @DisplayName("Cancel search")
    @Description("Init searching, wait for cancel button, tap it and wait for cancel button to disappear")
    @Severity(SeverityLevel.MINOR)
    public void testCancelSearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature("Search")
    @DisplayName("Amount of no empty search")
    @Description("Search 'Linkin Park discography' and assert there is more then 0 search results")
    @Severity(SeverityLevel.MINOR)
    public void testAmountOfNoEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue("We found too few results", amount_of_search_results > 0);
    }

    @Test
    @Feature("Search")
    @DisplayName("Empty search results label")
    @Description("Search 'lnubkghfdfvbgyun' and checking empty result label")
    @Severity(SeverityLevel.MINOR)
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "lnubkghfdfvbgyun";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.getEmptySearchResultLabel();
        SearchPageObject.assertThereIsNoSearchResult(search_line);
    }

    @Test
    @Feature("Search")
    @DisplayName("Search for element by title and description")
    @Description("Search article and check result with both values - title and description")
    @Severity(SeverityLevel.MINOR)
    public void testSearchForElementByTitleAndDescription()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String title = "Coffee";
        String description = "rewed beverage made from seeds of Coffee genus";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(title);
        SearchPageObject.waitForSearchResult(title);
        SearchPageObject.waitForElementByTitleAndDescription(title, description);
    }
    @Test
    @Feature("Search")
    @DisplayName("Search and check three results")
    @Description("Search articles by 'new year' and check three results by description")
    @Severity(SeverityLevel.MINOR)
    public void testSearchAndVerifyThreeResults()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        String search_line = "new year";

        String expected_description_1 = "Beginning of the calendar year";

        String expected_description_2 = "Celebration on last day of old year";

        String expected_description_3 = "Holiday that celebrates the new year";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(search_line);

        SearchPageObject.waitForSearchResult(expected_description_1);
        SearchPageObject.waitForSearchResult(expected_description_2);
        SearchPageObject.waitForSearchResult(expected_description_3);
    }
}
