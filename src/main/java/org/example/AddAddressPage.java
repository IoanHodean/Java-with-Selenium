package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddAddressPage extends BasePage{

public AddAddressPage(WebDriver driver){
    this.driver=driver;
}
private By firstNameInput=By.id("input-firstname");
private By lastNameInput=By.id("input-lastname");
private By companyInput=By.id("input-company");
private By addressOneInput=By.id("input-address-1");
private By adressTwoInput=By.id("input-address-2");
private By cityInput=By.id("input-city");
private By postcodeInput=By.id("input-postcode");

private By defaultAddressYesButton=By.xpath("//*[@id=\"content\"]/form/fieldset/div[10]/div/div[1]/label/input");
private By defaultAddressNoButton=By.xpath("//*[@id=\"content\"]/form/fieldset/div[10]/div/div[2]/label/input");
private By continueButton=By.xpath("//*[@id=\"content\"]/form/div/div[2]/input");
private By countryWarningSelector=By.xpath("//*[@class=\"text-danger\"]");


public void insertFirstName(String firstname){
    driver.findElement(firstNameInput).sendKeys(firstname);
}
public void insertLastName(String lastname){
    driver.findElement(lastNameInput).sendKeys(lastname);
}
public void insertCompany(String company){
    driver.findElement(companyInput).sendKeys(company);
}
public void insertAddressOne(String addressOne){
    driver.findElement(addressOneInput).sendKeys(addressOne);
}
public void insertAddressTwo(String addressTwo){
    driver.findElement(adressTwoInput).sendKeys(addressTwo);
}
public void insertCity(String city){
    driver.findElement(cityInput).sendKeys(city);
}
public void insertPostcode(String postcode){
    driver.findElement(postcodeInput).sendKeys(postcode);
}
public void selectCountry(String country){
    WebElement countryDropdownList=driver.findElement(By.id("input-country"));
    Select select=new Select(countryDropdownList);
    select.selectByVisibleText(country);
}
public void selectRegion(String region){
    WebElement regionDropdownList=driver.findElement(By.id("input-zone"));
    Select select=new Select(regionDropdownList);
    select.selectByVisibleText(region);
}
public void checkDefaultAddressYes(){
    driver.findElement(defaultAddressYesButton).click();
    }
    public void checkDefaultAddressNo(){
    driver.findElement(defaultAddressNoButton).click();
    }
    public void clickContinueButton(){
    driver.findElement(continueButton).click();
    }
public boolean getCountryWarning(){
    return driver.findElement(countryWarningSelector).isDisplayed();
}

}
