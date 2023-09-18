package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPage extends BasePage{
    public ChangePasswordPage(WebDriver driver){
        this.driver=driver;
    }
    private By passwordInput= By.id("input-password");
    private By passwordConfirmInput=By.id("input-confirm");
    private By continueButton=By.xpath("//*[@id=\"content\"]/form/div[3]/div[2]/input");
    private By mismatchText=By.xpath("//*[@id=\"content\"]/form/div[2]/div/div");
    private By invalidInputPassword=By.xpath("//*[@id=\"content\"]/form/div[1]/div/div");

    public void passwordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void passwordConfirmInput(String passwordConfirm){
        driver.findElement(passwordConfirmInput).sendKeys(passwordConfirm);
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
    public String getMismatchPasswordErrorText(){
        return driver.findElement(mismatchText).getText();
    }
    public String getInvalidInputPasswordErrorText(){
        return driver.findElement(invalidInputPassword).getText();
    }
}
