package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListsPageObject;
import lib.ui.ios.iosMyListsPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.mobile_web.MWMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        } else if (Platform.getInstance().isIos()) {
            return new iosMyListsPageObject(driver);
        }
        else {
            return new MWMyListsPageObject(driver);
        }
    }
}