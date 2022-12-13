package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidArticlePageObject;
import lib.ui.ArticlePageObject;
import lib.ui.ios.iosArticlePageObject;
import lib.ui.mobile_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else if (Platform.getInstance().isIos()){
            return new iosArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}
