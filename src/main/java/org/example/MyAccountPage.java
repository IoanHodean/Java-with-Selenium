package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{
public MyAccountPage(WebDriver driver){
    this.driver=driver;
}
    private By myAccount=By.xpath("//*[@id=\"content\"]/div[1]/h2");

    public String MyAccountText(){
        return driver.findElement(myAccount).getText();
    }
}
