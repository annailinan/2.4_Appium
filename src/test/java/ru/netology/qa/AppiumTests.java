package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.netology.qa.screens.MainScreenForHomeWork;
//import junit.framework.TestCase;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumTests {

    private AndroidDriver driver;

    private String textEmpty = "";
    private String nonEmptyText = "Something";

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "myEmulatedPhone");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void putEmptyText() {
        MainScreenForHomeWork screen = new MainScreenForHomeWork(driver);
        screen.textToBeChanged.isDisplayed();
        String initText = screen.textToBeChanged.getText();
        screen.userInput.isDisplayed();
        screen.userInput.sendKeys(textEmpty);
        screen.buttonChange.isDisplayed();
        screen.buttonChange.click();
        screen.textToBeChanged.isDisplayed();
        String finalText = screen.textToBeChanged.getText();
        Assertions.assertEquals(initText, finalText);
    }

    @Test
    public void openTextInNewActivity() {
        MainScreenForHomeWork screen = new MainScreenForHomeWork(driver);
        screen.userInput.isDisplayed();
        screen.userInput.sendKeys(nonEmptyText);
        screen.buttonActivity.isDisplayed();
        screen.buttonActivity.click();
        screen.text.isDisplayed();
        String finalText = screen.text.getText();
        Assertions.assertEquals(nonEmptyText, finalText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}