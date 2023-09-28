package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By checkoutButton = By.xpath("//*[@id=\"content\"]/div[2]/a[2]");
    private final By qtyFirstItemField = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/input");
    private final By updateQtyFirstItemButton = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/div/button[1]");
    private final By qtySecondItemField = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[4]/div/input");
    private final By updateQtySecondItemButton = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[4]/div/div/button[1]");
    private final By successModificationText = By.xpath("//*[@id=\"checkout-cart\"]/div[1]");


    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public void updateQtyFirstItem(String qty) {
        driver.findElement(qtyFirstItemField).clear();
        driver.findElement(qtyFirstItemField).sendKeys(qty);
        driver.findElement(updateQtyFirstItemButton).click();
    }

    public void updateQtySecondItem(String qty) {
        driver.findElement(qtySecondItemField).clear();
        driver.findElement(qtySecondItemField).sendKeys(qty);
        driver.findElement(updateQtySecondItemButton).click();
    }

    public String getSuccessModificationText() {
        return driver.findElement(successModificationText).getText();
    }

}
