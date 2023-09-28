package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class SearchResultPage extends BasePage {
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By resultItems = By.xpath(".//a[contains(@class,'text-ellipsis-2')]");


    public void clickFirstItem() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElements(resultItems).get(0).click();
    }

    public void clickSecondItem() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElements(resultItems).get(1).click();
    }

    public WebElement FirstItem() throws InterruptedException {
        Thread.sleep(1000);
        return driver.findElement(By.xpath("//*[@id=\"entry_212469\"]/div/div[1]"));
    }

    public void clickXItem(int x) {
        driver.findElements(resultItems).get(x).click();
    }
}
