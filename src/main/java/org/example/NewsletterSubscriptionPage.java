package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewsletterSubscriptionPage extends BasePage {
    public NewsletterSubscriptionPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By subscribeButtonYes = By.xpath("//*[@id=\"content\"]/form/fieldset/div/div/div[1]/label");
    private final By subscribeButtonNo = By.xpath("//*[@id=\"content\"]/form/fieldset/div/div/div[2]/label");
    private final By continueButton = By.xpath("//*[@id=\"content\"]/form/div/div[2]/input");
    public void clickSubscribeRadioButtonYes() {
        driver.findElement(subscribeButtonYes).click();
    }

    public void clickSubscribeRadioButtonNo() {
        driver.findElement(subscribeButtonNo).click();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }
}

