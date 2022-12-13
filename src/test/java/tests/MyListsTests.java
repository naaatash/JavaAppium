package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class MyListsTests extends CoreTestCase {
    private static final String NAME_OF_FOLDER = "My list",
    LOGIN = "AppiumAuto",
    PASSWORD = "Appium*auth";


    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String articleTitle = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }
        else {ArticlePageObject.addArticlesToMySaved();}
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            Auth.clickAuthButton();
            Auth.enterLoginData(LOGIN, PASSWORD);
            Auth.submitForm();

            String url = driver.getCurrentUrl();
            String new_url = url.substring(0, 11) + "m." + url.substring(11);
            driver.get(new_url);

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    articleTitle,
                    ArticlePageObject.getArticleTitle());
        }
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }

        MyListsPageObject.swipeArticleToDelete(articleTitle);
    }


    @Test
    public void testSaveTwoArticlesAndDeleteOneOfThem()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented");
        ArticlePageObject.waitForTitleElement();
        String articleTitle = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }
        else {ArticlePageObject.addArticlesToMySaved();}
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            Auth.clickAuthButton();
            Auth.enterLoginData(LOGIN, PASSWORD);
            Auth.submitForm();

            String url = driver.getCurrentUrl();
            String new_url = url.substring(0, 11) + "m." + url.substring(11);
            driver.get(new_url);

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login",
                    articleTitle,
                    ArticlePageObject.getArticleTitle());
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation");

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(NAME_OF_FOLDER);
        }
        else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(NAME_OF_FOLDER);
        }
        MyListsPageObject.swipeArticleToDelete(articleTitle);
    }
}
