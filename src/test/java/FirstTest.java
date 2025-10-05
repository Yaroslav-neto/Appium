import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTest {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "Some name");
        capabilities.setCapability("appium:app", "D:\\Project_Android\\2_2_HW\\sample\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
        capabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        capabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appium:ensureWebviewsHavePages", true);
        capabilities.setCapability("appium:nativeWebScreenshot", true);
        capabilities.setCapability("appium:newCommandTimeout", 3600);
        capabilities.setCapability("appium:connectHardwareKeyboard", true);

        driver = new AndroidDriver(getUrl(), capabilities);
    }

    @Test
    public void emptyStringText() {
        var status = driver.getStatus();
        driver.executeScript("mobile:getCurrentPackage");
        var caps = driver.getSessionDetails();

        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();

        el1.sendKeys("   ");

        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el2.isDisplayed();
        el2.click();

        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el2.isDisplayed();

        Assertions.assertEquals("Hello UiAutomator!", el3.getText());
    }

    @Test
    public void newText() {
        var status = driver.getStatus();
        driver.executeScript("mobile:getCurrentPackage");
        var caps = driver.getSessionDetails();

        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();

        el1.sendKeys("123");

        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el2.isDisplayed();
        el2.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBe(By.id("ru.netology.testing.uiautomator:id/text"), "123"));

        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        el3.isDisplayed();


        Assertions.assertEquals("123", el3.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
