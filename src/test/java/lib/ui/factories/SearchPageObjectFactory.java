package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidSearchPageObject;
import lib.ui.SearchPageObject;
import lib.ui.ios.iosSearchPageObject;
import lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver){
        if(Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        }
        else if (Platform.getInstance().isIos()){
            return new iosSearchPageObject(driver);
        }
        else {
            return new MWSearchPageObject(driver);
        }
    }
}
