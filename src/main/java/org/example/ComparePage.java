package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComparePage extends BasePage {
    public ComparePage(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;
    private final By addToCartFirstItem = By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[2]/input");


    private final By removeFromCompareFirstItem = By.xpath("//a[@class=\"btn btn-danger btn-block\"]");

    private final By addToCartSecondItem = By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[3]/input");

    private final By noProductText = By.xpath("//*[@id=\"content\"]/p");
    private final By continueButton = By.xpath("//*[@id='content']/div/div/a");

    public void addToCartItems() {
        driver.findElement(addToCartFirstItem).click();
        driver.findElement(addToCartSecondItem).click();

    }

    public void removeItems() {
        for (int i = 0; i < driver.findElements(By.xpath("//*[@id=\"content\"]/table/tbody[1]/tr[1]/td")).size(); i++) {
            driver.findElement(removeFromCompareFirstItem).click();
        }
    }

    public void removeItems(int nr) {
        for (int i = 0; i < nr; i++) {
            driver.findElement(removeFromCompareFirstItem).click();
        }
    }


    public String getNoProductText() {
        return driver.findElement(noProductText).getText();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }
}
