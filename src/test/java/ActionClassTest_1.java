import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

//Action Class Test:Version 1
public class ActionClassTest_1 {
    @Test
    public void actionClass() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String mainPageUrl = "https://demoqa.com/slider";
        driver.navigate().to(mainPageUrl);

        //slider
        String sliderCssSelector = "#sliderContainer > div.col-9 > span > input";
        WebElement slider = driver.findElement(By.cssSelector(sliderCssSelector));
        driver.manage().window().maximize();


        Actions actions = new Actions(driver);
        actions.clickAndHold(slider);
        for (int i = 1; i <= 25 ; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        actions.release();

    }
}
