package ru.avatarex.uc;

import ru.avatarex.Utils;
import ru.avatarex.pages.MainPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class UcTests {

    @Test
    void logoutTest() {
        WebDriver webDriver = Utils.getDriver();
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.tryLogin(Utils.LOGIN, Utils.PASSWORD);
        mainPage.tryLogout();
        WebElement account = Utils.findElementOrNull(webDriver, By.xpath("//a[contains(@class, 'tDT48') and @href='/settings/account']"));
        assertNull(account);
        webDriver.quit();
    }

    @Test
    void Subscribe() {
        WebDriver webDriver = Utils.getDriver();
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.tryLogin(Utils.LOGIN, Utils.PASSWORD);
        WebElement answer;
        try {
            answer = webDriver.findElement(By.xpath("(//*[contains(@class, 'kqme1')])[1]//*[contains(@class, 'f68ED')]"));
            answer.click();
        } catch (Exception e) {
            answer = null;
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        assertNotNull(answer);

        webDriver.quit();
    }

    @Test
    void Unsubscribe() {
        WebDriver webDriver = Utils.getDriver();
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.tryLogin(Utils.LOGIN, Utils.PASSWORD);
        webDriver.get("https://www.tumblr.com/following");
        String lastText = webDriver.findElement(By.xpath("(//*[contains(@class, 'rZlUD')])")).getText();
        WebElement elementToClick = webDriver.findElement(By.xpath("(//*[contains(@class, 'rZlUD')])[1]//*[contains(@class, 'xWjHY')]/*[contains(@class, 'TRX6J')]/*[contains(@class, 'EvhBA')]"));
        elementToClick.click();
        String newText = webDriver.findElement(By.xpath("(//*[contains(@class, 'rZlUD')])")).getText();

        assertNotEquals(lastText, newText);
        webDriver.quit();
    }

    @Test
    void loginTest() {
        WebDriver webDriver = Utils.getDriver();
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.tryLogin(Utils.LOGIN, Utils.PASSWORD);

        WebElement account = Utils.getElementBySelector(webDriver, By.xpath("//a[contains(@class, 'tDT48') and @href='/settings/account']"));

        assertNotNull(account);

        webDriver.quit();
    }

    @Test
    void wrongLoginTest() {
        WebDriver webDriver = Utils.getDriver();

        MainPage mainPage = new MainPage(webDriver);
        webDriver.get(Utils.BASE_URL);
        mainPage.tryLogin(Utils.LOGIN, Utils.WRONG_PASSWORD);
        WebElement account = Utils.findElementOrNull(webDriver, By.xpath("//a[contains(@class, 'tDT48') and @href='/settings/account']"));
        assertNull(account);
        webDriver.quit();
    }
}