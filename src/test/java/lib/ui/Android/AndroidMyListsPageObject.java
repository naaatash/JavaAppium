package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {
    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    static {
        ELEMENT_BY_NAME_TPL = "xpath://*[contains(@text, '{ELEMENT_NAME}')]";
    }
}
