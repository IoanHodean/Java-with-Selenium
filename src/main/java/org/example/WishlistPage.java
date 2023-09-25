package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WishlistPage extends BasePage {
    public WishlistPage(WebDriver driver) {
        this.driver = driver;

    }

    private By noResultsElement = By.xpath(".//div[@id='content']/p");
    private By searchButton = By.xpath(".//button[@class='type-text']");
    private By addToCartFirstItem=By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/button");
    private By removeFromCartFirstItem=By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/a");
    private By notificationsBoxTop=By.xpath("//*[@id='notification-box-top']/div/div/div/p");
    private By successAlert=By.xpath("//*[@id=\"account-wishlist\"]/div[1]");
    private By closeButtonSuccessAlert=By.xpath("//*[@id=\"account-wishlist\"]/div[1]/button");

    public String getNoResultsElementText() {
        return driver.findElement(noResultsElement).getText();
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
    public void clickAddToCartFirstItem(){
        driver.findElement(addToCartFirstItem).click();
    }
    public String getNotificationText(){
        return driver.findElement(notificationsBoxTop).getText();
    }
    public void clickRemoveFromWishlistFirstItem(){
        driver.findElement(removeFromCartFirstItem).click();
    }
    public String getSuccessAlert(){
        return driver.findElement(successAlert).getText();
    }
    public void clickCloseSuccessfulModification(){
        driver.findElement(closeButtonSuccessAlert).click();
    }

    public int howManyItemsInWishlist(){
        int nr=0;

        List<WebElement> list=driver.findElements(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr"));
        nr=list.size();


        return nr;
        }
    }




