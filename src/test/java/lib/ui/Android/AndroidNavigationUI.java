package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";
    }
    public AndroidNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
