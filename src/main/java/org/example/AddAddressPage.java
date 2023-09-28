package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddAddressPage extends BasePage {

    public AddAddressPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstNameInput = By.id("input-firstname");
    private final By lastNameInput = By.id("input-lastname");
    private final By companyInput = By.id("input-company");
    private final By addressOneInput = By.id("input-address-1");
    private final By addressTwoInput = By.id("input-address-2");
    private final By cityInput = By.id("input-city");
    private final By postcodeInput = By.id("input-postcode");

        private final By continueButton = By.xpath("//*[@id=\"content\"]/form/div/div[2]/input");
    private final By countryWarningSelector = By.xpath("//*[@class=\"text-danger\"]");


    public void insertFirstName(String firstname) {
        driver.findElement(firstNameInput).sendKeys(firstname);
    }

    public void insertLastName(String lastname) {
        driver.findElement(lastNameInput).sendKeys(lastname);
    }

    public void insertCompany(String company) {
        driver.findElement(companyInput).sendKeys(company);
    }

    public void insertAddressOne(String addressOne) {
        driver.findElement(addressOneInput).sendKeys(addressOne);
    }

    public void insertAddressTwo(String addressTwo) {
        driver.findElement(addressTwoInput).sendKeys(addressTwo);
    }

    public void insertCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void insertPostcode(String postcode) {
        driver.findElement(postcodeInput).sendKeys(postcode);
    }

    public void selectCountry(String country) {
        WebElement countryDropdownList = driver.findElement(By.id("input-country"));
        Select select = new Select(countryDropdownList);
        select.selectByVisibleText(country);
    }

    public void selectRegion(String region) {
        WebElement regionDropdownList = driver.findElement(By.id("input-zone"));
        Select select = new Select(regionDropdownList);
        select.selectByVisibleText(region);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public boolean getCountryWarning() {
        return driver.findElement(countryWarningSelector).isDisplayed();
    }

}
