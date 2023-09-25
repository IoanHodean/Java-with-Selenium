package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver){
        this.driver=driver;
    }
private By cartEmptyText= By.xpath("//*[@id=\"content\"]/p");
    private By continueShoppingButton=By.xpath("//*[@id=\"content\"]/div[2]/a[1]");
    private By checkoutButton=By.xpath("//*[@id=\"content\"]/div[2]/a[2]");
    private By qtyFirstItemField=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/input");
    private By updateQtyFirstItemButton=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/div/button[1]");
       private By removeFirstItemButton=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/div/button[2]");
        private By qtySecondItemField=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[4]/div/input");
        private By removeSecondItemButton=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[4]/div/div/button[2]");
    private By updateQtySecondItemButton=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[4]/div/div/button[1]");
    private By successModificationText=By.xpath("//*[@id=\"checkout-cart\"]/div[1]");

    public String getCartEmptyText() {
        return driver.findElement(cartEmptyText).getText();
    }
    public void clickContinueShoppingButton(){
        driver.findElement(continueShoppingButton).click();
    }
    public void clickCheckoutButton(){
        driver.findElement(checkoutButton).click();
    }
    public void updateQtyFirstItem(String qty){
        driver.findElement(qtyFirstItemField).clear();
         driver.findElement(qtyFirstItemField).sendKeys(qty);
        driver.findElement(updateQtyFirstItemButton).click();
    }
    public void updateQtySecondItem(String qty){
        driver.findElement(qtySecondItemField).clear();
         driver.findElement(qtySecondItemField).sendKeys(qty);
        driver.findElement(updateQtySecondItemButton).click();
    }
       public void clickRemoveFirstItemButton(){
        driver.findElement(removeFirstItemButton).click();
    }
    public void clickRemoveSecondItemButton(){
        driver.findElement(removeSecondItemButton).click();
    }
    public String getSuccessModificationText(){
        return driver.findElement(successModificationText).getText();
    }

}
