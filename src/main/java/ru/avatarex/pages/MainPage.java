package ru.avatarex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.avatarex.Utils;

import java.time.Duration;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void tryLogin(String login, String password) {
        WebElement loginButton = Utils.getElementBySelector(driver, By.xpath("//button[contains(@class, 'CxLjL')]"));
        loginButton.click();

        WebElement loginByEmailButton = Utils.getElementBySelector(driver, By.xpath("//button[contains(@class, 'dKGjO')]"));
        loginByEmailButton.click();

        WebElement loginInput = Utils.getElementBySelector(driver, By.xpath("//input[@type='email']"));
        loginInput.clear();
        loginInput.sendKeys(login);
        WebElement continueButton = Utils.getElementBySelector(driver, By.xpath("//button[contains(@class, 'TRX6J') and contains(@class, 'CxLjL') and contains(@class, 'qjTo7') and contains(@class, 'CguuB') and contains(@class, 'qNKBC')]"));
        continueButton.click();
        WebElement passwordInput = Utils.getElementBySelector(driver, By.xpath("//input[contains(@class, 'sL4Tf')]"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        WebElement finalLoginButton = Utils.getElementBySelector(driver, By.xpath("//button[@type='submit']"));

        finalLoginButton.click();
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException ignored){}
    }

    public void tryLogout() {
        driver.findElement(By.xpath("//*[@id='account_button']//*[@class='a132D']")).click();

        driver.findElement(By.xpath("//*[contains(@class, 'Vp4ma')]")).click();

        driver.findElement(By.xpath("(//*[contains(@class, 'CxLjL')])[2]//*[contains(@class, 'EvhBA')]")).click();

        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException ignored){}
    }

}
