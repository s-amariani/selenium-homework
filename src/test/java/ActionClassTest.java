import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


//ActionClassTest:Version 2
public class ActionClassTest {

    WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void actions() throws InterruptedException {

        String mainPageUrl = "https://demoqa.com/slider";
        driver.navigate().to(mainPageUrl);

        //slider
        String sliderCssSelector = "#sliderContainer > div.col-9 > span > input";
        WebElement slider = driver.findElement(By.cssSelector(sliderCssSelector));
        //slider  value
        int sliderValue = Integer.parseInt(slider.getAttribute("valueAsNumber"));

        driver.manage().window().maximize();

        System.out.println(sliderValue);

        Actions actions = new Actions(driver);
        actions.clickAndHold(slider);


        sliderValue *= 2;
        Thread.sleep(2000);
        //move slider to default value
        while (sliderValue != 25) {
            Thread.sleep(100);
            actions.sendKeys(Keys.ARROW_LEFT).build().perform();
            sliderValue--;
            System.out.println("move slider to default value:"+sliderValue);
        }

        System.out.println("after moving slider to default value :"+sliderValue);

        Thread.sleep(3000);

        //move slider up to 50
        while (sliderValue!=50){
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            Thread.sleep(100);
            sliderValue++;
            System.out.println("Move slider point up to 50:"+sliderValue);
        }
        actions.release();
        System.out.println("after moving slider up to 50:"+sliderValue);

        //or
        /*
        for (int i = 0; i < 25; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            Thread.sleep(200);
            System.out.println(sliderValue);
        }
         */
        actions.release();
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }

}
