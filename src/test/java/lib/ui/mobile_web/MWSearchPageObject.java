package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject{
    static {
        SEARCH_INIT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "scc:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        EMPTY_SEARCH_RESULT_LABEL = "css:ul.page-list>li.page-summary";
        SEARCH_RESULT_LOCATOR = "css:p.without-results";
    }
    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}