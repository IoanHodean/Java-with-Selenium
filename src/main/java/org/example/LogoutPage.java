package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By logoutMessage = By.xpath("//*[@id=\"content\"]/p[2]");
    private final By logoutContinueButton = By.xpath("//*[@id=\"content\"]/div/a");

    public String getLogoutMessage() {
        return driver.findElement(logoutMessage).getText();
    }

    public void clickContinue() {
        driver.findElement(logoutContinueButton).click();
    }
}
