import org.example.RegisterAccountPage;
import org.example.SearchResultPage;
import org.example.WishlistPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class WishlistFlowTest {
    private WebDriver driver;
    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private SearchResultPage searchResultPage;

    private String loginPageURL= "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUp() {
        System.out.println("Initialize driver.");
        driver = new ChromeDriver();
        registerAccountPage= new RegisterAccountPage(driver);
        wishlistPage= new WishlistPage(driver);
        searchResultPage= new SearchResultPage(driver);
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        createAccount();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigate to " + loginPageURL);
    }
    @Test
    public void addItemToWishList() throws InterruptedException {
        wishlistPage.clickWishlist();
        String actualResult= wishlistPage.getNoResultsElementText();
        String expectedResult="No results!";
        Assert.assertEquals(actualResult,expectedResult,"Text from element is not the expected one!");
        wishlistPage.enterTextSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        searchResultPage.clickFirstItem();


    }
    public void createAccount(){
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


