package ru.avatarex;

import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Utils {

    static Dotenv dotenv = Dotenv.configure().load();

    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = dotenv.get("CHROME_SYSTEM_PROPERTY_PATH");
    public static final String FIREFOX_SYSTEM_PROPERTY_NAME = "webdriver.gecko.driver";
    public static final String FIREFOX_SYSTEM_PROPERTY_PATH = dotenv.get("FIREFOX_SYSTEM_PROPERTY_PATH");
    public static final String BASE_URL = "http://www.tumblr.com/";
    public static final String LOGIN = dotenv.get("LOGIN");
    public static final String PASSWORD = dotenv.get("PASSWORD");
    public static final String WRONG_PASSWORD = "wrong_password";

    public static WebDriver getDriver() {
        prepareDrivers();
        String driverType = getDriverType();
        System.out.println(driverType);
        return switch (driverType) {
            case "chrome" -> getChromeDriver();
            case "firefox" -> getFirefoxDriver();
            default -> throw new RuntimeException("Веб-драйвер не указан");
        };
    }

    private static ChromeDriver getChromeDriver() {
        if (!System.getProperties().containsKey(CHROME_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Драйвер Chrome установлен неправильно");
        }
        return new ChromeDriver();
    }

    private static FirefoxDriver getFirefoxDriver() {
        if (!System.getProperties().containsKey(FIREFOX_SYSTEM_PROPERTY_NAME)) {
            throw new RuntimeException("Драйвер Firefox установлен неправильно");
        }

        return new FirefoxDriver();
    }

    private static String getDriverType() {
        Properties properties = new Properties();
        try (InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty("webdriver.type");
            } else {
                throw new RuntimeException("Нет файла с настройками!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла с настройками!");
        }
    }

    public static void prepareDrivers() {
        assert CHROME_SYSTEM_PROPERTY_PATH != null;
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
        assert FIREFOX_SYSTEM_PROPERTY_PATH != null;
        System.setProperty(FIREFOX_SYSTEM_PROPERTY_NAME, FIREFOX_SYSTEM_PROPERTY_PATH);
    }

    public static WebElement getElementBySelector(WebDriver driver, By selector) {

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(13));
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static WebElement findElementOrNull(WebDriver driver, By by) {
        try {
            return driver.findElement(by);
        } catch (Exception e) {
            return null;
        }
    }
}