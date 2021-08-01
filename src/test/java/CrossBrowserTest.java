import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CrossBrowserTest {
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{

        //Check if parameter passed as 'chrome'
        if(browser.equalsIgnoreCase("chrome")){

            /*
            //set path to chromedriver.exe
            WebDriverManager.chromedriver().setup();
            //create chrome instance
            ChromeDriver driver = new ChromeDriver();
            */
            driver = new HtmlUnitDriver(BrowserVersion.CHROME);
        }
        //Check if parameter passed as 'Edge'
        else if(browser.equalsIgnoreCase("Edge")){
            /*
            //set path to Edge.exe
            WebDriverManager.edgedriver().setup();
            //create Edge instance
            EdgeDriver driver = new EdgeDriver();
            */
            driver = new HtmlUnitDriver(BrowserVersion.EDGE);
        }
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public  void CrossBrowser(){

        //Run all test  in two different browsers using WebDriverManager for drivers
        String mainPageUrl = "http://demo.guru99.com/test/login.html";
        driver.navigate().to(mainPageUrl);

        String emailId = "email";
        WebElement email = driver.findElement(By.id(emailId));

        email.sendKeys("test@gmail.com");
        System.out.println(email.getAttribute("value"));

    }
}
