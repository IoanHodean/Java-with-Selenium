package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By myAccount = By.xpath("//*[@id=\"content\"]/div[1]/h2");
    private final By myAccountSuccessAlert = By.xpath("//*[@id=\"account-account\"]/div[1]");
    private final By titleDiv = By.xpath("//div[@class=\"card mb-3\"]/h2");
    private final By affiliateButton = By.xpath("//*[@id=\"content\"]/div[3]/div");


    public String myAccountText() {
        return driver.findElement(myAccount).getText();
    }

    public String successAlert() {
        return driver.findElement(myAccountSuccessAlert).getText();
    }

    public String getTitle() {
        return driver.findElement(titleDiv).getText();
    }

    public List<WebElement> Elements() {
        return driver.findElements(By.xpath("//a[@class=\"d-inline-flex text-decoration-none text-reset flex-column my-3\"]"));
    }

    public String getAffiliateButtonText() {
        return driver.findElement(affiliateButton).getText();
    }

}
