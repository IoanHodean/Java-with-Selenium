package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {
    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By paragraphElement = By.xpath("//*[@id=\"content\"]/p[2]");
    private final By successPasswordUpdate = By.xpath("//*[@id=\"account-account\"]/div[1]");

    public String getParagraphText() {
        return driver.findElement(paragraphElement).getText();

    }

    public String getSuccessText() {
        return driver.findElement(successPasswordUpdate).getText();
    }
}
