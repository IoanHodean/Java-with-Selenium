import org.example.LoginPage;
import org.example.MyAccountPage;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver= new ChromeDriver();
    @Test
    public void loginWithValidCredentialsTest(){


        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.insertEmail("hodean.ioan@gmail.com");
        loginPage.insertPassword("idunno");
        loginPage.clickButton();
        MyAccountPage myAccountPage=new MyAccountPage(driver);
        String myAccount=myAccountPage.MyAccountText();
        Assert.assertEquals(myAccount,"My Account");
    }
    @Test
    public void loginWithInvalidCredentialsTest(){

        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.insertEmail("hodean.ioan@gmail.com");
        loginPage.insertPassword("iduno");
        loginPage.clickButton();
        String errorMessage=loginPage.warning();
        Assert.assertEquals(errorMessage,"Warning: No match for E-Mail Address and/or Password.");
    }
    @Test
    public void loginWithNoEmailTest(){
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.insertPassword("iduno");
        loginPage.clickButton();
        String url=loginPage.warning();
        Assert.assertEquals(url," Warning: No match for E-Mail Address and/or Password.");
    }
    @Test
    public void loginMultipleTimesTest(){
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        LoginPage loginPage= new LoginPage(driver);
        for (int i=0;i<5;i++) {
            loginPage.clear();
            loginPage.insertEmail("hodean.ioan@gmail.com");
            loginPage.insertPassword("iduno");
            loginPage.clickButton();
        }
        String warning= loginPage.warning();
        Assert.assertEquals(warning,"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.");
        }


@AfterTest
    public void close(){
        driver.quit();
}


}
