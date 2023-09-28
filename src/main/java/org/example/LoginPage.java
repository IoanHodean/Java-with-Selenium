package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By emailInput = By.id("input-email");
    private final By passwordInput = By.id("input-password");
    private final By loginButton = By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/input");
    private final By warning = By.xpath(".//div[@class='alert alert-danger alert-dismissible']");
    private final By successfulEmailSent = By.xpath("//*[@id=\"account-login\"]/div[1]");
    private final By forgottenPasswordHyperlink = By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/div[2]/a");
    private final By logOutFromNavBar = By.xpath("//*[@id=\"widget-navbar-217834\"]/ul/li[6]/ul/li[6]/a");
    private final By logOutFromColumn = By.xpath("//a[@class=\"list-group-item\"][13]");

    public void insertEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void insertPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickButton() {
        driver.findElement(loginButton).click();
    }

    public String warning() {
        return driver.findElement(warning).getText();
    }

    public void clickForgottenPasswordHyperlink() {
        driver.findElement(forgottenPasswordHyperlink).click();
    }

    public String emailConfirmationLink() {
        return driver.findElement(successfulEmailSent).getText();
    }

    public void clickLogOutFromNavBar() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"widget-navbar-217834\"]/ul/li[6]"))).perform();
        driver.findElement(logOutFromNavBar).click();
    }

    public void clickLogOutFromColumn() {
        driver.findElement(logOutFromColumn).click();
    }

}

