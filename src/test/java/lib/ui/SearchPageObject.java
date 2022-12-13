package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
        SEARCH_INIT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_LOCATOR,
        EMPTY_SEARCH_RESULT_LABEL,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_TITLE_TPL,
        SEARCH_RESULT_DESCRIPTION_TPL;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getSearchResultElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getSearchResultTitle(String title)
    {
        return SEARCH_RESULT_TITLE_TPL.replace("{SUBSTRING}", title);
    }
    private static String getSearchResultDescription(String description)
    {
        return SEARCH_RESULT_DESCRIPTION_TPL.replace("{SUBSTRING}", description);
    }
    /* TEMPLATES METHODS */
    @Step("Wait for element with expected title and description")
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String searchResultTitleXpath = getSearchResultTitle(title);
        String searchResultDescriptionXpath = getSearchResultDescription(description);
        this.waitForElementPresent(searchResultTitleXpath, "Cannot find search result by title. Check xpath: " + searchResultTitleXpath, 5);
        this.waitForElementPresent(searchResultDescriptionXpath, "Cannot find result by description. Check xpath: " + searchResultDescriptionXpath, 5);
    }
    @Step("Wait for Cancel button to appear")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Search cancel button", 5);
    }
    @Step("Wait for cancel button to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementAbsence(SEARCH_CANCEL_BUTTON, "Cannot find Search cancel button", 5);
    }
    @Step("Click 'cancel' in search line")
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Search cancel button", 5);
    }
    @Step("Initializing the search field")
    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT, "Cannot click search_init_element");
    }
    @Step("Typing '{search_line}' in search line")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search_input", 5);
    }
    @Step("Wait for any search result")
    public void waitForSearchResult(String element_text)
    {
        this.waitForElementPresent(getSearchResultElement(element_text), "Cannot find search result with substring " + element_text);
    }
    @Step("Click by Article with expected substring")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }
    @Step("Get amount of elements with locator")
    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    @Step("Get amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(SEARCH_RESULT_LOCATOR, "Cannot find found articles");
        return getAmountOfElements(SEARCH_RESULT_LOCATOR);
    }
    @Step("Wait for empty result label to appear")
    public void getEmptySearchResultLabel()
    {
        this.waitForElementPresent(EMPTY_SEARCH_RESULT_LABEL, "Cannot find empty result label", 5);
    }
    @Step("Waiting for no results by search line")
    public void assertThereIsNoSearchResult(String search_text)
    {
        this.waitForElementAbsence(getSearchResultElement(search_text), "Seems like Search results is here " + SEARCH_RESULT_LOCATOR, 5);
    }
}
