import org.example.ComparePage;
import org.example.SearchResultPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompareTest {
    private SearchResultPage searchResultPage;
    private WebDriver driver;
    private ComparePage comparePage;
    public By searchInput = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div[2]/input");
    public By searchButton = By.xpath("//*[@id=\"search\"]/div[2]/button");
    public By compareIcon = By.xpath("//*[@id=\"entry_217823\"]/a");
    public String homepage = "https://ecommerce-playground.lambdatest.io/index.php?route=common/home";
    private By compareClick = By.xpath("//*[@id=\"entry_216844\"]/button");

    private By addToCartFirstItem = By.xpath("//*[@id=\"content\"]/table/tbody[3]/tr/td[2]/input");
    private By addToCartSecondItem = By.xpath("//*[@id=\"content\"]/table/tbody[3]/tr/td[3]/input");

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        searchResultPage = new SearchResultPage(driver);
        comparePage = new ComparePage(driver);
        driver.get(homepage);
    }

    @Test
    public void CompareTwoProducts() throws InterruptedException {
        driver.findElement(searchInput).sendKeys("Apple Cinema 30\"");
        driver.findElement(searchButton).click();
        searchResultPage.clickFirstItem();
        driver.findElement(compareClick).click();
        driver.navigate().back();
        searchResultPage.clickSecondItem();
        driver.findElement(compareClick).click();
        driver.findElement(compareIcon).click();
        Assert.assertNotNull(addToCartFirstItem);
        Assert.assertNotNull(addToCartSecondItem);
    }

    @Test
    public void CompareWithNoProducts() {
        driver.findElement(compareIcon).click();
        Assert.assertEquals(comparePage.getNoProductText(), "You have not chosen any products to compare.");
    }

    @Test
    public void ComparePageContinueButtonTest() {
        driver.findElement(compareIcon).click();
        comparePage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
    }

    @Test
    public void CompareAndAddToCart() throws InterruptedException {
        driver.findElement(searchInput).sendKeys("iphone");
        driver.findElement(searchButton).click();
        searchResultPage.clickFirstItem();
        driver.findElement(compareClick).click();
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys("imac");
        driver.findElement(searchButton).click();
        searchResultPage.clickFirstItem();
        driver.findElement(compareClick).click();
        driver.findElement(compareIcon).click();
        comparePage.addToCartItems();
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[1]/a/img")));
    }

    @Test
    public void CompareAndRemoveItems() throws InterruptedException {
        driver.findElement(searchInput).sendKeys("iphone");
        driver.findElement(searchButton).click();
        searchResultPage.clickFirstItem();
        driver.findElement(compareClick).click();
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys("imac");
        driver.findElement(searchButton).click();
        searchResultPage.clickFirstItem();
        driver.findElement(compareClick).click();
        driver.findElement(compareIcon).click();
        comparePage.removeFromCompareItems();
        Assert.assertNotNull(comparePage.getNoProductText());
    }

    @Test
    public void CompareAndRemoveSomeItemsAddToCartOtherItems() throws InterruptedException {
        //this test adds four items in compare, eliminate the first two and add the other two to the cart
        String[] list = {"iphone", "imac", "samsung", "htc"};
        for (int i = 0; i < list.length; i++) {
            driver.findElement(searchInput).sendKeys(list[i]);
            driver.findElement(searchButton).click();
            searchResultPage.clickFirstItem();
            driver.findElement(compareClick).click();
            driver.findElement(searchInput).clear();
        }
        driver.findElement(compareIcon).click();
        comparePage.removeFromCompareItems();
        comparePage.addToCartItems();
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
        //the assertion is that the second item in cart in present
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[1]/a/img")));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

