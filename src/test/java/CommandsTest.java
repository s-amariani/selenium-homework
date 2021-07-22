import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class CommandsTest {

    @Test
    public void dynamic_controls() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        //clicking on button "Enable"
        WebElement btnEnable = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/button"));
        btnEnable.click();
        // waiting 7 seconds after clicking the button so that the input field can turn on
        WebDriverWait wait = new WebDriverWait(driver, 7);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[1]/form[2]/button")));
        //checking that input field is enabled
        WebElement input = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/input"));
        System.out.println("\"input field is enabled\": " + input.isEnabled());
        //checking that text 'It's enabled!' is displayed
        WebElement msgDisplayed = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/p"));
        System.out.println("\"text \"It's enabled!\"is displayed\":" + msgDisplayed.isDisplayed());

        if (btnEnable.getText()=="Disable")
        {
            System.out.println("Button text is changed to:" + btnEnable.getText());
        }else
            System.out.println("Button text is changed to:" + btnEnable.getText());
        //writing "Bootcamp" in  input field and clear it
        input.sendKeys("Bootcamp");
        input.clear();
    }

    @Test
    public void drag_and_drope(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        //Checking that Y coordinate of column A and column B are the same
        int element_a= driver.findElement(By.id("column-a")).getLocation().getY();
        int element_b= driver.findElement(By.id("column-b")).getLocation().getY();
        System.out.println("Column A y.coordinate:"+element_a+ "    Column B y.coordinate: "+element_b);
    }
}

