package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:.mw-page-title-main";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#ca-watch";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:[title='Remove this page from your watchlist']";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
