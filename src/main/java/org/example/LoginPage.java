package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public LoginPage (WebDriver driver){
        this.driver=driver;
    }
    private By emailInput= By.id("input-email");
    private By passwordInput=By.id("input-password");
    private By loginButton=By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/input");
    private By warning=By.xpath(".//div[@class='alert alert-danger alert-dismissible']");
    private final By successfulEmailSent=By.xpath("//*[@id=\"account-login\"]/div[1]");
    private By forgottenPasswordHyperlink=By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/form/div[2]/a");
    private By logOutFromNavBar=By.partialLinkText("Logout");
    private By logOutFromColumn=By.xpath("//*[@id=\"column-right\"]/div/a[14]");
    public void insertEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }
public void insertPassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
}
public void clickButton(){
        driver.findElement(loginButton).click();
}
public String warning(){
       return driver.findElement(warning).getText();
}
public void clickForgottenPasswordHyperlink(){
        driver.findElement(forgottenPasswordHyperlink).click();
}
public String emailConfirmationLink(){
        return driver.findElement(successfulEmailSent).getText();
}
public void clickLogOutFromNavBar(){
        driver.findElement(logOutFromNavBar).click();
}
public void clickLogOutFromColumn(){
        driver.findElement(logOutFromColumn).click();
}
public void clear() {
        driver.findElement(emailInput).clear();
        driver.findElement(passwordInput).clear();
}
}

