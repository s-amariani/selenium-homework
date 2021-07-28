import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebTablesTest {
    @Test
    public void WebTables(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "http://techcanvass.com/Examples/webtable.html";
        driver.navigate().to(mainPageUrl);

        String hondaPriceXpath = "//*[@id='t01']/tbody/tr[4]/td[3]";
        WebElement hondaPrice = driver.findElement(By.xpath(hondaPriceXpath));
        System.out.println(hondaPrice.getText());
    }
}
