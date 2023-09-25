package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.TestUtil.generateRandomEmail;

public class ForgotPasswordPage extends BasePage {
    public ForgotPasswordPage(WebDriver driver) {this.driver = driver;}
    private By emailInput= By.id("input-email");
    private By continueButton=By.xpath("//*[@id=\"content\"]/form/div/div[2]/button");
    private By warningText=By.xpath("//*[@id=\"account-forgotten\"]/div[1]");
    public void insertRandomEmail(){
    driver.findElement(emailInput).sendKeys(generateRandomEmail());
}
    public void insertEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
    public String warning(){
        return driver.findElement(warningText).getText();
    }
}
