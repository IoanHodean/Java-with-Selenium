package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAccountPage extends BasePage {
    public RegisterAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstNameInput = By.id("input-firstname");
    private final By lastNameInput = By.id("input-lastname");
    private final By emailInput = By.id("input-email");
    private final By telephoneInput = By.id("input-telephone");
    private final By passwordInput = By.id("input-password");
    private final By passwordConfirmInput = By.id("input-confirm");
    private final By subscribeButtonYes = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/div[1]/label");
    private final By subscribeButtonNo = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/div[2]/label");
    private final By privacyPolicyCheckbox = By.xpath("//label[@for='input-agree']");
    private final By continueButton = By.xpath("//input[@value='Continue']");
    private final By firstNameErrorElement = By.xpath("//input[@name= 'firstname']/following-sibling::div");
    private final By lastNameErrorElement = By.xpath("//input[@name= 'lastname']/following-sibling::div");
    private final By emailErrorElement = By.xpath("//input[@name= 'email']/following-sibling::div");
    private final By telephoneErrorElement = By.xpath("//input[@name= 'telephone']/following-sibling::div");
    private final By passwordErrorElement = By.xpath("//input[@name= 'password']/following-sibling::div");
    private final By confirmPasswordErrorElement = By.xpath("//input[@name= 'confirm']/following-sibling::div");

    public void insertFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void insertLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void insertEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void insertPhoneNumber(String phoneNumber) {
        driver.findElement(telephoneInput).sendKeys(phoneNumber);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void setPasswordConfirmInput(String passwordConfirm) {
        driver.findElement(passwordConfirmInput).sendKeys(passwordConfirm);
    }

    public void checkPrivacyPolicy() {
        driver.findElement(privacyPolicyCheckbox).click();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public String getFirstNameErrorMessage() {
        return driver.findElement(firstNameErrorElement).getText();
    }

    public String getLastNameErrorMessage() {
        return driver.findElement(lastNameErrorElement).getText();
    }

    public String getEmailErrorMessage() {
        return driver.findElement(emailErrorElement).getText();
    }

    public String getTelephoneErrorMessage() {
        return driver.findElement(telephoneErrorElement).getText();
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(passwordErrorElement).getText();
    }

    public String getConfirmationPasswordErrorMessage() {
        return driver.findElement(confirmPasswordErrorElement).getText();
    }

    public void subscribeYes() {
        driver.findElement(subscribeButtonYes).click();
    }

    public void subscribeNo() {
        driver.findElement(subscribeButtonNo).click();
    }
}

