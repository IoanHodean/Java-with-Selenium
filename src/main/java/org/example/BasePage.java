package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class BasePage {

    WebDriver driver;
    private final By errorMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    private final By wishlistHeartElement = By.xpath("//a[@aria-label='Wishlist']");
    private final By searchInput = By.name("search");

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();

    }

    public void clickWishlist() {
        driver.findElement(wishlistHeartElement).click();
    }

    public void enterTextSearch(String searchText) {
        driver.findElement(searchInput).sendKeys(searchText);
    }


}
