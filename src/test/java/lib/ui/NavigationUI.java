package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
    MY_LISTS_LINK,
    OPEN_NAVIGATION;
    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click OPEN_NAVIGATION button",5);
        }
        else {
            System.out.println("Method openNavigation() not needed when Platform != 'mobile_web'");
        }
    }
    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Cannot find navigation button to My list",15 );
        } else {
        this.waitForElementAndClick(
               MY_LISTS_LINK, "Cannot find navigation button to My list");
        }
    }
}
