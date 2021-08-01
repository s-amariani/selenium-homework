import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.TreeMap;

public class CookieTest {
@Test
    public void Cookie() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

       String mainPageUrl = "http://demo.guru99.com/test/cookie/selenium_aut.php";
        driver.navigate().to(mainPageUrl);

        //Username credentials
        String  usernameCssSelector = ".form-control[name='username']";
        WebElement username = driver.findElement(By.cssSelector(usernameCssSelector));
        String inputUsernameTestData = "username@gmail.com";
        username.sendKeys(inputUsernameTestData);

        //Password credentials
        String  passwordCssSelector = ".form-control[name='password']";
        WebElement password = driver.findElement(By.cssSelector(passwordCssSelector));
        String passwordTestData = "password123";
        password.sendKeys(passwordTestData);

        //Printing username and password
        System.out.println(username.getAttribute("value"));
        System.out.println(password.getAttribute("value"));

        String  btnLoginCssSelector = "[name='submit']";
        WebElement btnLogin = driver.findElement(By.cssSelector(btnLoginCssSelector));

        btnLogin.click();

        driver.navigate().refresh();

        System.out.println(driver.manage().getCookieNamed("Selenium"));

        driver.manage().deleteCookieNamed("Selenium");

        //check that cookie with name Selenium is deleted
        System.out.println(driver.manage().getCookieNamed("Selenium"));

        Thread.sleep(6000);
        driver.quit();
    }
}
