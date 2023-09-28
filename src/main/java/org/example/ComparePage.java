package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComparePage extends BasePage{
    public ComparePage(WebDriver driver){
        this.driver=driver;
    }
    private SearchResultPage searchResultPage;
    private WebDriver driver;
    private By addToCartFirstItem= By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[2]/input");


    private By removeFromCompareFirstItem=By.xpath("//a[@class=\"btn btn-danger btn-block\"]");

    private By addToCartSecondItem=By.xpath("//*[@id=\"content\"]/table/tbody[2]/tr/td[3]/input");

    private By searchInput=By.name("search");
    private By searchButton=By.xpath("//*[@id=\"search\"]/div[2]/button");
    private By noProductText=By.xpath("//*[@id=\"content\"]/p");
    private By continueButton=By.xpath("//*[@id='content']/div/div/a");
    private By tableHead=By.xpath("//thead");

public void addToCartItems(){
    driver.findElement(addToCartFirstItem).click();
    driver.findElement(addToCartSecondItem).click();

}
    public void removeItems() {
      for (int i=0;i<driver.findElements(By.xpath("//*[@id=\"content\"]/table/tbody[1]/tr[1]/td")).size();i++){
          driver.findElement(removeFromCompareFirstItem).click();
      }
    }
    public void removeItems(int nr){
    for (int i=0;i<nr;i++){
        driver.findElement(removeFromCompareFirstItem).click();
    }
    }


    public String getNoProductText(){
        return driver.findElement(noProductText).getText();
    }

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }
}
