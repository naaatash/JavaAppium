package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON = "xpath://*/a[text()='Log in']",
    LOGIN_INPUT = "css:input[name='wpName']",
    PASSWORD_INPUT = "css:input[name='wpPassword']",
    SUBMIT_BUTTON = "css:button[type='submit']";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot enter login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot enter password", 5);
    }
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 5);
    }
}
