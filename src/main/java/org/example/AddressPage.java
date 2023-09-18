package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddressPage extends BasePage{
    public AddressPage(WebDriver driver){
        this.driver=driver;
    }
    private By successText= By.xpath("//*[@id=\"account-address\"]/div[1]");
    public String getSuccessText(){
        return driver.findElement(successText).getText();
    }

}
