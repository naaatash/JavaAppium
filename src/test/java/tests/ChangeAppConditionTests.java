//package tests;
//
//import lib.CoreTestCase;
//import lib.Platform;
//import lib.ui.ArticlePageObject;
//import lib.ui.SearchPageObject;
//import lib.ui.factories.ArticlePageObjectFactory;
//import lib.ui.factories.SearchPageObjectFactory;
//import org.junit.Test;
//import org.openqa.selenium.ScreenOrientation;
//
//public class ChangeAppConditionTests extends CoreTestCase {
//    @Test
//    public void testChangeScreenOrientationOnSearchResults()
//    {
//        if (Platform.getInstance().isMW()){
//            return;
//        }
//        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.clickByArticleWithSubstring("Object-oriented");
//        String title_before_rotation = ArticlePageObject.getArticleTitle();
//        System.out.println(title_before_rotation);
//        this.rotateScreenLandscape();
//        String title_after_rotation = ArticlePageObject.getArticleTitle();
//        assertEquals("Article title have been changed after screen rotation", title_before_rotation, title_after_rotation);
//        this.rotateScreenPortrait();
//        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
//        assertEquals("Article title have been changed after screen rotation", title_after_rotation, title_after_second_rotation);
//    }
//    @Test
//    public void testCheckArticleInBackground()
//    {
//        if (Platform.getInstance().isMW()){
//            return;
//        }
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//        SearchPageObject.initSearchInput();
//        SearchPageObject.typeSearchLine("Java");
//        SearchPageObject.waitForSearchResult("Object-oriented");
//        this.backgroundApp(2);
//        SearchPageObject.waitForSearchResult("Object-oriented");
//    }
//
//    @Test
//    public void testWithLandscapeOrientation(){
//        if (Platform.getInstance().isMW()){
//            return;
//        } else {
//        driver.rotate(ScreenOrientation.LANDSCAPE);
//        }
//    }
//    @Test
//    public void testCheckPortraitOrientation(){
//        if (Platform.getInstance().isMW()){
//            return;
//        }
//        String expectedOrientation = "PORTRAIT";
//        String actualOrientation = driver.getOrientation().toString();
//        assertEquals(expectedOrientation, actualOrientation);
//    }
//    @Test
//    public void secondTestWithLandscapeOrientation(){
//        if (Platform.getInstance().isMW()){
//            return;
//        }
//        driver.rotate(ScreenOrientation.LANDSCAPE);
//    }
//}
