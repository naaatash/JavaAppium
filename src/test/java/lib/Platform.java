package lib;

import com.sun.javafx.collections.MappingChange;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";

    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static Platform instance;
    private Platform(){}

    public static Platform getInstance() {
        if(instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid())
        {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }
        else if(this.isIos())
        {
            return new IOSDriver(URL, this.getiOSDesiredCapabilities());
        }
        else if(this.isMW())
        {
            return new ChromeDriver(this.getMWChromeOptions());
        }
        else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }
    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIos()
    {
        return isPlatform(PLATFORM_IOS);
    }
    public boolean isMW() {return isPlatform(PLATFORM_MOBILE_WEB);}
    public String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/user/Projects/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
    return capabilities;
    }

    public DesiredCapabilities getiOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14");
        capabilities.setCapability("platformVersion", "16.1");
        capabilities.setCapability("appPackage", "/Users/user/Projects/JavaAppiumAutomation/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D)");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }
}
