import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BonusHomeWorkTest {
    WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void FlipkartTest() throws InterruptedException {

        String mainPageUrl = "http://www.flipkart.com";
        driver.navigate().to(mainPageUrl);

        //Phone number,email input
        String inputPhoneNumberXpath = "//div/input[@class='_2IX_2- VJZDxU']";
        WebElement inputPhoneNumber = driver.findElement(By.xpath(inputPhoneNumberXpath));
        //Enter Phone Number.
        inputPhoneNumber.sendKeys("599432981");

        //Password input
        String inputPasswordXpath = "//div/input[@class='_2IX_2- _3mctLh VJZDxU']";
        WebElement inputPassword = driver.findElement(By.xpath(inputPasswordXpath));
        //Enter password.
        inputPassword.sendKeys("testpassword123");

        //button login
        String btnLoginCssSelector = "button._2KpZ6l._2HKlqd._3AWRsL";
        WebElement btnLogin = driver.findElement(By.cssSelector(btnLoginCssSelector));
        //Click login button.
        btnLogin.click();

        String spanValidatorCssSelector = "span._2YULOR > span";
        WebElement spanValidator = driver.findElement(By.cssSelector(spanValidatorCssSelector));

        WebDriverWait wait = new WebDriverWait(driver,2);
        wait.until(ExpectedConditions.visibilityOf(spanValidator));

        //Verify that Email ID/Mobile number is not  valid
        if (spanValidator.isDisplayed()){
            System.out.println("Email ID/Mobile number is not  valid");
        }
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(7000);
        driver.quit();
    }
}
