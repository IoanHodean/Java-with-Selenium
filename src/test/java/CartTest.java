import org.example.CartPage;
import org.example.ComparePage;
import org.example.SearchResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTest {

WebDriver driver;
SearchResultPage searchResultPage;
    public String homepage = "https://ecommerce-playground.lambdatest.io/index.php?route=common/home";
    public By searchInput = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div[2]/input");
    public By searchButton = By.xpath("//*[@id=\"search\"]/div[2]/button");
    private By addToCartButton=By.xpath("//*[@id=\"entry_216842\"]/button");
    private By qtyFirstItemField=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[4]/div/input");
    private By qtySecondItemField=By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[4]/div/input");
    public String cartURL="https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart";
    private CartPage cartPage;
    @BeforeClass
    public void setUp() throws InterruptedException {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        searchResultPage = new SearchResultPage(driver);
        cartPage=new CartPage(driver);
        driver.get(homepage);
        driver.findElement(searchInput).sendKeys("iphone");
        driver.findElement(searchButton).click();
        searchResultPage.clickFirstItem();
        driver.findElement(addToCartButton).click();
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys("iphone");
        driver.findElement(searchButton).click();
        searchResultPage.clickSecondItem();
        driver.findElement(addToCartButton).click();
        driver.get(cartURL);
    }


    @Test
    public void goToCheckout(){
    cartPage.clickCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://ecommerce-playground.lambdatest.io/index.php?route=checkout/checkout");
    }
    @Test
    public void UpdateQtyTwoItemsVerifySuccessAlert(){
        cartPage.updateQtyFirstItem("2");
        cartPage.updateQtySecondItem("2");
        Assert.assertEquals(cartPage.getSuccessModificationText(),"Success: You have modified your shopping cart!\n" +
                "×");
    }

//@AfterTest
    public void tearDown(){
        driver.quit();
}




}
