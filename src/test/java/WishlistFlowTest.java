import org.example.LoginPage;
import org.example.RegisterAccountPage;
import org.example.SearchResultPage;
import org.example.WishlistPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import static util.TestUtil.generateRandomEmail;

public class WishlistFlowTest {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private SearchResultPage searchResultPage;
    private LoginPage loginPage;
    Actions action;


    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        registerAccountPage = new RegisterAccountPage(driver);
        wishlistPage = new WishlistPage(driver);
        searchResultPage = new SearchResultPage(driver);
        loginPage = new LoginPage(driver);
        action = new Actions(driver);
        System.out.println("Initialize driver.");
    }


    @Test
    public void addItemToWishlistFromProductPage() throws InterruptedException {
        createAccount();
        wishlistPage.clickWishlist();
        String actualResult = wishlistPage.getNoResultsElementText();
        String expectedResult = "No results!";
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one!");
        wishlistPage.enterTextSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        searchResultPage.clickFirstItem();
        driver.findElement(By.xpath("//button[@title=\"Add to Wish List\"]")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"notification-box-top\"]/div/div[2]/div[1]/p")).getText(), """
                Success: You have added
                Apple Cinema 30"
                to your
                wish list
                !""");
        Thread.sleep(10000);
        loginPage.clickLogOutFromNavBar();
    }

    @Test
    public void addItemToWishlistWithoutAccount() throws InterruptedException {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
        wishlistPage.enterTextSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        searchResultPage.clickFirstItem();
        driver.findElement(By.xpath("//button[@title=\"Add to Wish List\"]")).click();
        Thread.sleep(1000);
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"notification-box-top\"]/div")));
    }

    @Test
    public void addItemToWishlistFromSearchPage() throws InterruptedException {
        createAccount();
        wishlistPage.enterTextSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        action.moveToElement(searchResultPage.FirstItem()).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"entry_212469\"]/div/div[1]/div/div[1]/div[2]/button[2]")).click();
        searchResultPage.clickWishlist();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[2]/a")).getText(), "Apple Cinema 30\"");
        loginPage.clickLogOutFromNavBar();
    }

    @Test
    public void addItemToWishListAndThenToCart() throws InterruptedException {
        createAccount();
        wishlistPage.enterTextSearch("imac");
        wishlistPage.clickSearchButton();
        action.moveToElement(searchResultPage.FirstItem()).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"entry_212469\"]/div/div[1]/div/div[1]/div[2]/button[2]")).click();
        searchResultPage.clickWishlist();
        wishlistPage.clickAddToCartFirstItem();
        Thread.sleep(1000);
        Assert.assertEquals(wishlistPage.getNotificationText(), "Success: You have added\n" +
                "iMac\n" +
                "to your\n" +
                "shopping cart\n" +
                "!");
        Thread.sleep(10000);
        loginPage.clickLogOutFromNavBar();
    }

    @Test
    public void addItemToWishlistAndRemoveIt() throws InterruptedException {
        createAccount();
        wishlistPage.enterTextSearch("imac");
        wishlistPage.clickSearchButton();
        action.moveToElement(searchResultPage.FirstItem()).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title=\"Add to Wish List\"]")).click();
        searchResultPage.clickWishlist();
        wishlistPage.clickRemoveFromWishlistFirstItem();
        Assert.assertEquals(wishlistPage.getSuccessAlert(), "Success: You have modified your wish list!\n" +
                "×");
        loginPage.clickLogOutFromNavBar();
    }


    public void addFiveItemsToWishlist() throws InterruptedException {
        createAccount();
        wishlistPage.enterTextSearch("imac");
        wishlistPage.clickSearchButton();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            searchResultPage.clickXItem(i);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@title=\"Add to Wish List\"]")).click();
            driver.navigate().back();
        }
        searchResultPage.clickWishlist();
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[5]")));
    }

    @Test
    public void AddAllItemsFromWishlistToCart() throws InterruptedException {
        addFiveItemsToWishlist();
        int nr = wishlistPage.howManyItemsInWishlist();
        System.out.println("There are " + nr + " products in Wishlist.");
        for (int i = 0; i < nr; i++) {
            driver.findElements(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/button")).get(i).click();
        }
        Thread.sleep(10000);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");
        Assert.assertNotNull(driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[" + nr + "]")));
        loginPage.clickLogOutFromNavBar();
    }

    @Test
    public void RemoveAllItemsFromWishlistLastToFirst() throws InterruptedException {
        addFiveItemsToWishlist();
        int nr = wishlistPage.howManyItemsInWishlist();
        System.out.println("There are " + nr + " products in Wishlist.");
        for (int i = nr - 1; i >= 0; i--) {
            driver.findElements(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/a")).get(i).click();
        }
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText(), "No results!");

        loginPage.clickLogOutFromNavBar();
    }

    @Test
    public void RemoveAllItemsFromWishlistFirstToLast() throws InterruptedException {
        addFiveItemsToWishlist();
        int nr = wishlistPage.howManyItemsInWishlist();
        System.out.println("There are " + nr + " products in Wishlist.");
        for (int i = 0; i < 5; i++) {
            driver.findElements(By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]/a")).get(0).click();
        }
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/p")).getText(), "No results!");
        loginPage.clickLogOutFromNavBar();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void createAccount() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        System.out.println("Creating new account to be use in tests...");
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("0745168174");
        registerAccountPage.setPassword("Password123");
        registerAccountPage.setPasswordConfirmInput("Password123");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
        System.out.println("Creating new account to be use in tests...Done");
    }
}


