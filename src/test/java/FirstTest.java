import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FirstTest {

    @Test
    public void firstTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://testfasttrackit.info/magento-test/");
        WebElement logoClick = driver.findElement(By.className("logo"));
        logoClick.click();
        String x = driver.getCurrentUrl();
        Assert.assertEquals(x, "http://testfasttrackit.info/magento-test/");
        driver.quit();

    }

}
