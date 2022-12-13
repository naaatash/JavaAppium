package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
    TITLE,
    FOOTER_ELEMENT,
    OPTIONS_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT,
    OK_BUTTON,
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
    CLOSE_ARTICLE_BUTTON;
    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 20);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
        return titleElement.getAttribute("text");
        }
        else if (Platform.getInstance().isIos()){
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find footer element", 15
            );
        }
        else if (Platform.getInstance().isIos()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    15);
        }
        else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    15);
        }
    }

    public void addArticleToMyList(String nameOfFolder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON, "Cannot find element");
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find element");
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY, "Cannot find element");
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT, "Cannot find and clear: " + MY_LIST_NAME_INPUT);
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT, nameOfFolder, "Cannot find input or type", 5);
        this.waitForElementAndClick(
                OK_BUTTON, "Cannot find OK button");
    }
    public void closeArticle()
    {
        if (Platform.getInstance().isMW()){
            System.out.println("Method closeArticle() not needed on mobile web platform");
        }
        else{
            this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON, "Cannot find element");
        }
    }
    public void addArticlesToMySaved(){
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot click option to add article to reading list", 5);
    }
    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot find and click 'remove article from saved' button", 1);
        this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find button to add an article to saved list after removing it from this list before", 3);
        }
    }
}
