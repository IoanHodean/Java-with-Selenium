package org.example;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ComparePage extends BasePage{
    public ComparePage(WebDriver driver){
        this.driver=driver;
    }
    private SearchResultPage searchResultPage;
    private WebDriver driver;
    private By addToCartFirstItem= By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[2]/input");


    private By removeFromCompareFirstItem=By.xpath("//*[@id='content']/table/tbody[2]/tr/td[2]/a");

    private By addToCartSecondItem=By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[3]/input");
    private By removeFromCompareSecondItem=By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[3]/a");


    private By searchInput=By.name("search");
    private By searchButton=By.xpath("//*[@id=\"search\"]/div[2]/button");
    private By noProductText=By.xpath("//*[@id=\"content\"]/p");
    private By continueButton=By.xpath("//*[@id='content']/div/div/a");

public void addToCartItems(){
    driver.findElement(addToCartFirstItem).click();
    driver.findElement(addToCartSecondItem).click();

}
    public void removeFromCompareItems(){
    driver.findElement(removeFromCompareFirstItem).click();
    driver.findElement(removeFromCompareFirstItem).click();
    }
    public String getNoProductText(){
        return driver.findElement(noProductText).getText();
    }
    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
}
