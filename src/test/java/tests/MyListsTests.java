package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
@Epic("My lists")
public class MyListsTests extends CoreTestCase {
    private static final String NAME_OF_FOLDER = "My list",
    LOGIN = "AppiumAuto",
    PASSWORD = "Appium*auth";

    @Test
    @Features(value = {
            @Feature(value = "Search"),
            @Feature(value = "Article"),
            @Feature(value = "My lists")
    })
    @DisplayName("Save first article")
    @Description("Search 'Java' and save article with 'Object-oriented' description to empty list")
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
            Assert.assertEquals("We are not on the same page after login",
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
    @Features(value = {
            @Feature(value = "Search"),
            @Feature(value = "Article"),
            @Feature(value = "My lists")
    })
    @DisplayName("Save two articles")
    @Description("Search and save two articles and then delete one of them from list")
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
        String no_needed_articleTitle = ArticlePageObject.getArticleTitle();

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
            Assert.assertEquals("We are not on the same page after login",
                    no_needed_articleTitle,
                    ArticlePageObject.getArticleTitle());
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation");
        String needed_articleTitle = ArticlePageObject.getArticleTitle();

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
        MyListsPageObject.swipeArticleToDelete(no_needed_articleTitle);

        MyListsPageObject.waitForArticleApearByTitle(needed_articleTitle);
    }
}
