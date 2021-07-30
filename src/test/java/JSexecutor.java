import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.htmlunit.corejs.javascript.Script;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.TreeMap;

public class JSexecutor {
    @Test
    public void FileUpload() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "http://webdriveruniversity.com/To-Do-List/index.html";
        driver.navigate().to(mainPageUrl);

        String itemPracticeMagicXpath = "//*[text()=' Practice magic']";
        WebElement itemPracticeMagic = driver.findElement(By.xpath(itemPracticeMagicXpath));


        //Hover on 'Practice Magic' to do item using Action Class, moveToElement
        Actions actions = new Actions(driver);
        actions.moveToElement(itemPracticeMagic).perform();

        //Delete item using JS Executor
        WebElement btnDeleteItem = driver.findElement(By.cssSelector("#container > ul > li:nth-child(3) > span > i"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",btnDeleteItem);

        Thread.sleep(4000);

    }

    @Test
    public void Scroll() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        String mainPageUrl = "http://webdriveruniversity.com/Scrolling/index.html ";
        driver.navigate().to(mainPageUrl);

        //Scroll to the left box with 'Entries' text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String entriesCssSelector= "#zone2-entries";
        WebElement entries  = driver.findElement(By.cssSelector(entriesCssSelector));

        Actions actions = new Actions(driver);
        actions.moveToElement(entries).perform();
        js.executeScript("window.scrollBy(0,800)");

        //Validate text with JS executor
        String textOfEntriesbtn = js.executeScript("return document.documentElement.innerText;").toString();
        System.out.println(textOfEntriesbtn);
    }
}
