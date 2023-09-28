package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishlistPage extends BasePage {
    public WishlistPage(WebDriver driver) {
        this.driver = driver;

    }

    private final By noResultsElement = By.xpath(".//div[@id='content']/p");
    private final By searchButton = By.xpath(".//button[@class='type-text']");
    private final By addToCartFirstItem = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/button");
    private final By removeFromCartFirstItem = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/a");
    private final By notificationsBoxTop = By.xpath("//*[@id='notification-box-top']/div/div/div/p");
    private final By successAlert = By.xpath("//*[@id=\"account-wishlist\"]/div[1]");

    public String getNoResultsElementText() {
        return driver.findElement(noResultsElement).getText();
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void clickAddToCartFirstItem() {
        driver.findElement(addToCartFirstItem).click();
    }

    public String getNotificationText() {
        return driver.findElement(notificationsBoxTop).getText();
    }

    public void clickRemoveFromWishlistFirstItem() {
        driver.findElement(removeFromCartFirstItem).click();
    }

    public String getSuccessAlert() {
        return driver.findElement(successAlert).getText();
    }

    public int howManyItemsInWishlist() {
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr"));
        return list.size();
    }
}




