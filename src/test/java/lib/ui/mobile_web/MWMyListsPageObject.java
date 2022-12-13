package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ELEMENT_BY_NAME_TPL = "xpath://h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "css:li[title='{TITLE}']>a.watched";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
