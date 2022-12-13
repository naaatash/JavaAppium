package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{
    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE = "id:Add or edit preferred language",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started",
    SKIP = "id:Skip";

    @Step("Wait for 'learn more' link")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find " + STEP_LEARN_MORE_LINK + " link", 10);
    }

    @Step("Wait for 'new waus to explore' link")
    public void waitForNewWaysToExploreLink()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find " + STEP_NEW_WAYS_TO_EXPLORE + " link", 10);
    }

    @Step("Wait for add or edit preferred language")
    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE, "Cannot find " + STEP_ADD_OR_EDIT_PREFERRED_LANGUAGE + " link", 10);
    }

    @Step("Click 'Next' button")
    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click " + NEXT_LINK + " button", 10);
    }

    @Step("Wait for 'Learn more about data collected' link")
    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find " + STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK + " link", 10);
    }

    @Step("Click 'Get started' button")
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click " + GET_STARTED_BUTTON + " button", 10);
    }

    @Step("Click 'Skip' button")
    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP, "Cannot find and click 'skip' button");
    }
}
