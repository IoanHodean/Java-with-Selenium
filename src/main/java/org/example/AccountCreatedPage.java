package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {
    public AccountCreatedPage(WebDriver driver) {this.driver = driver;}

    private By paragraphElement = By.xpath("//*[@id=\"content\"]/p[2]");

    public String getParagraphText() {
        return driver.findElement(paragraphElement).getText();

    }
}
