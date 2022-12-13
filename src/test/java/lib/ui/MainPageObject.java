package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;
    public MainPageObject(RemoteWebDriver driver)
    {
        this.driver = driver;
    }

    @Step("Wait for element present by locator '{locator}'")
    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds){
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public WebElement waitForElementPresent(String locator, String error_message){
        return waitForElementPresent(locator, error_message, 5);
    }

    @Step("Try click element '{locator}' with few attempts")
    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try{
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e){
                if (current_attempts > amount_of_attempts){
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++ current_attempts;
        }
    }

    @Step("Get amount of elements by '{locator}'")
    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    @Step("Return amount of elements '{locator}'")
    public Boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }

    @Step("Wait for element '{locator}' and click it. Default timeout")
    public WebElement waitForElementAndClick(String locator, String error_message){
        WebElement element = waitForElementPresent(locator, error_message);
        element.click();
        return element;
    }

    @Step("Wait for element '{locator}' and click it. Timeout '{timeoutInSeconds}'")
    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    @Step("Wait for element '{locator}' and send value '{value}'. Timeout '{timeoutInSeconds}'")
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, int timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    @Step("Wait for element '{locator}' not present. Timeout '{timeoutInSeconds}'")
    public boolean waitForElementAbsence(String locator, String error_message, long timeoutInSeconds){
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    @Step("Wait for element '{locator}' and clear")
    public WebElement waitForElementAndClear(String locator, String error_message){
        WebElement element = waitForElementPresent(locator, error_message);
        element.clear();
        return element;
    }

    @Step("Assert element '{locator}' has text")
    public void assertElementHasText(String locator, String expectedValue, String error_message){
        WebElement element = waitForElementPresent(locator, error_message);
        String actualValue = element.getText();
        Assert.assertEquals(error_message,expectedValue,
                actualValue);
    }

    @Step("Swipe up. Duration is {timeOfSwipe} millis")
    public void swipeUp(int timeOfSwipe){
        if (driver instanceof AppiumDriver){
        TouchAction action = new TouchAction((AppiumDriver)driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int)(size.height * 0.8);
        int end_y = (int)(size.height * 0.2);

        action
                .press(PointOption.point(x, start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x, end_y))
                .release()
                .perform();
        }
        else {
            System.out.println("Method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Swipe up quick (200 millis)")
    public void swipeUpQuick(){
        swipeUp(200);
    }
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while (driver.findElements(getLocatorByString(locator)).size() == 0){
            if (already_swiped > max_swipes){
                waitForElementPresent(locator, error_message);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    @Step("Swipe element '{locator}' to the left")
    public void swipeElementToLeft(String locator, String error_message){
        if (driver instanceof AppiumDriver){
        WebElement element = waitForElementPresent(
                locator,
                error_message);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction((AppiumDriver)driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(150)));
        if(Platform.getInstance().isAndroid()){
            action.moveTo(PointOption.point(left_x, middle_y));
        }
        else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x, 0));
        }
        action.release();
        action.perform();
        }
        else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Get locator by string '{locator_with_type}'")
    public By getLocatorByString(String locator_with_type)
    {
        String[] explored_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explored_locator[0];
        String locator = explored_locator[1];

        switch (by_type){
            case "xpath":
                return By.xpath(locator);
            case "id":
                return By.id(locator);
            case "css":
                return By.cssSelector(locator);
            default:
                throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    @Step("Check is element '{locator}' located on the screen")
    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator" + locator, 1).getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return (element_location_by_y < screen_size_by_y);
    }

    @Step("Swipe up till element '{locator}' appear. Max swipes '{max_swipes}'")
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while (this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    @Step("Click element '{locator}' to the right upper corner")
    public void clickElementToTheRightUpperCorner(String locator, String error_message){
        if (driver instanceof AppiumDriver){
        WebElement element = this.waitForElementPresent(locator  + "/..", error_message);
        int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        int width = element.getSize().getWidth();

        int point_to_click_x = (right_x + width) - 3;
        int point_to_click_y = middle_y;

        TouchAction action = new TouchAction((AppiumDriver)driver);
        action.tap(PointOption.point(point_to_click_x, point_to_click_y));
        }
        else {
            System.out.println("Method clickElementToTheRightUpperCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Scroll web page up")
    public void scrollWebPageUp(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }
        else {
            System.out.println("Method scrollWebPageUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Scroll till element '{locator}' not visible")
    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    @Step("Take screenshot")
    public String takeScreenshot(String name){
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken " + path);
        } catch (Exception e){
            System.out.println("Cannot take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path){
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e){
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}
