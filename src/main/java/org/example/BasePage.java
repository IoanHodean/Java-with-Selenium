package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

 WebDriver driver;
 private By errorMessage= By.xpath("//div[@class='alert alert-danger alert-dismissible']");
 private By wishlistheartElement= By.xpath("//a[@aria-label='Wishlist']");
 private By searchInput= By.name("search");
 public String getErrorMessage(){
  return driver.findElement(errorMessage).getText();

 }
 public void clickWishlist(){
  driver.findElement(wishlistheartElement).click();
 }
 public void enterTextSearch(String searchText){
  driver.findElement(searchInput).sendKeys(searchText);
 }


}
