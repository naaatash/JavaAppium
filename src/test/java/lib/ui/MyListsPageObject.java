package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
    ELEMENT_BY_NAME_TPL, REMOVE_FROM_SAVED_BUTTON;
    private static String getElementXpathByName(String nameOfFolder)
    {
        return ELEMENT_BY_NAME_TPL.replace("{TITLE}", nameOfFolder);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Open folder by name '{nameOfFolder}'")
    public void openFolderByName(String nameOfFolder)
    {
        this.waitForElementAndClick(getElementXpathByName(nameOfFolder), "Cannot find folder by name " + nameOfFolder);
    }

    @Step("Get 'Remove' button by article title '{title}'")
    public String getRemoveButtonByTitle(String title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", title);
    }

    @Step("Swipe article to delete. Only for mobile apps")
    public void swipeArticleToDelete(String articleTitle)
    {
        this.waitForArticleApearByTitle(articleTitle);

        if (Platform.getInstance().isIos() || Platform.getInstance().isAndroid()){
            this.swipeElementToLeft(getElementXpathByName(articleTitle), "Cannot find and swipe article");
            this.waitForArticleDisapearByTitle(articleTitle);
        }
        else {
            String remove_locator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(remove_locator, "Cannot click button to remove article from saved", 5);
        }

        if(Platform.getInstance().isIos()){
            this.clickElementToTheRightUpperCorner(articleTitle, "Cannot find and swipe element to delete. Locator:" + articleTitle);
        }

        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
            this.waitForArticleDisapearByTitle(articleTitle);
        }
    }

    @Step("Wait for article not present by title {articleTitle}")
    public void waitForArticleDisapearByTitle(String articleTitle)
    {
        String articleTitle_xpath = getElementXpathByName(articleTitle);
        this.waitForElementAbsence(articleTitle_xpath, "Article absence test failed", 5);
    }

    @Step("Wait for article appear by title '{articleTitle}'")
    public void waitForArticleApearByTitle(String articleTitle)
    {
        String articleTitle_xpath = getElementXpathByName(articleTitle);
        this.waitForElementPresent(articleTitle_xpath, "Cannot find article", 5);
    }

}
