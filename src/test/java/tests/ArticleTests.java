package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {
            @Feature(value = "Search"),
            @Feature(value = "Article")
    })
    @DisplayName("Compare article title with expected one")
    @Description("Search 'Java' article, select search result with 'Object-oriented' description, open it and check the name")
    @Step("Starting test 'testCompareArticleTitle'")
    @Severity(SeverityLevel.BLOCKER)
    public void testCompareArticleTitle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented");
        String articleTitle = ArticlePageObject.getArticleTitle();

//        ArticlePageObject.takeScreenshot("Article page");

        Assert.assertEquals(
                "Java (programming language)",
                articleTitle);
    }

    @Test
    @DisplayName("Swipe article to the footer")
    @Description("Open article and swipe it to the footer")
    @Step("Starting test 'testSwipeArticle'")
    @Severity(SeverityLevel.MINOR)
    public void testSwipeArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @DisplayName("Save and then delete article")
    @Features(value = {
            @Feature(value = "Search"),
            @Feature(value = "Article")
    })
    @Severity(SeverityLevel.NORMAL)
    public void testSaveAndDeleteArticle(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        String nameOfFolder = "My list";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented");
        ArticlePageObject.waitForTitleElement();
        String articleTitle = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isMW()){
            ArticlePageObject.addArticlesToMySaved();
        }
        else {
            ArticlePageObject.addArticleToMyList(nameOfFolder);
        }
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyLists();
        MyListsPageObject.openFolderByName(nameOfFolder);
        MyListsPageObject.swipeArticleToDelete(articleTitle);
    }
}
