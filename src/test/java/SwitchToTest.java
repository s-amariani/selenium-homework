import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class SwitchToTest {

    @Test
    public void Frame1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "http://the-internet.herokuapp.com/iframe";
        driver.navigate().to(mainPageUrl);

        //Switching to iframe
        String iFrameId = "mce_0_ifr";
        driver.switchTo().frame(iFrameId);
        //Writing "Here Goes" int text field (1st way)
        String textFieldXpath = "//body[@id='tinymce']/p";
        WebElement textField = driver.findElement(By.xpath(textFieldXpath));

        textField.sendKeys( Keys.LEFT_CONTROL + "a" + Keys.DELETE);
        textField.sendKeys("Here Goes");
        //switching to main frame
        driver.switchTo().defaultContent();
        //Clicking on 'Align center' icon
        String btnAlignCenterXpath ="//button[@aria-label='Align center']";
        WebElement btnAlignCenter = driver.findElement(By.xpath(btnAlignCenterXpath));
        btnAlignCenter.click();

        Thread.sleep(5000);
        driver.quit();

    }

    @Test
    public  void Iframe2(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/iframe");
        //Writing "Here Goes" int text field (2nd way)
        WebElement btnFile = driver.findElement(By.xpath("//button/span[contains(text(),'File')]"));
        btnFile.click();

        WebElement btnNewDocument = driver.findElement(By.cssSelector("div[class='tox-collection__item-label']"));
        btnNewDocument.click();

        driver.switchTo().frame(0);
        WebElement textField = driver.findElement(By.xpath("//body[@id='tinymce']/p"));
        textField.sendKeys("Here Goes");

        driver.switchTo().parentFrame();

        WebElement btnAlignCenter = driver.findElement(By.xpath("//button[@aria-label='Align center']"));
        btnAlignCenter.click();


    }

    @Test
    public void Alerts() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl="https://demoqa.com/alerts";
        driver.navigate().to(mainPageUrl);

        //Clicking on the first 'Click me' button and  accepting unexpected alert
        String btnAlertCssSelector = "#alertButton";
        WebElement btnAlert = driver.findElement(By.cssSelector(btnAlertCssSelector));
        btnAlert.click();
        //switching to alert
        driver.switchTo().alert().accept();
        //switching to main frame
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
        driver.quit();
    }
}
