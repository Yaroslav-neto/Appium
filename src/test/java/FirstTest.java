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

    enum Platform {Andoid}

    ;
    Platform platform = Platform.Andoid;
    AppObjects appObjects;

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

        appObjects = new AppObjects(driver);
    }

    @Test
    public void emptyStringText() {

        appObjects.inputFld.isDisplayed();
        appObjects.inputFld.click();

        appObjects.inputFld.sendKeys("   ");

        appObjects.btnChange.isDisplayed();
        appObjects.btnChange.click();

        appObjects.chgFld.isDisplayed();

        Assertions.assertEquals("Hello UiAutomator!", appObjects.chgFld.getText());
    }

    @Test
    public void newText() {
        appObjects.inputFld.isDisplayed();
        appObjects.inputFld.click();

        appObjects.inputFld.sendKeys("123");

        appObjects.btnActiv.isDisplayed();
        appObjects.btnActiv.click();

        By textLocator = By.id(appObjects.actvFld.getAttribute("resource-id"));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(textLocator));

        Assertions.assertEquals("123", appObjects.actvFld.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
