import io.github.bonigarcia.wdm.WebDriverManager;
import okio.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;



public class Exceptions {
    @Test
    public void DatePicker() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "https://jqueryui.com/datepicker/ ";
        driver.navigate().to(mainPageUrl);

        try {
            String iFrameCssSelector = ".demo-frame";
            WebElement iFrame = driver.findElement(By.cssSelector(iFrameCssSelector));
            driver.switchTo().frame(iFrame);
            /* if we write wrong iFrame,for example we try to switch to iframe with wrong index  we can catch NoSuchFrameException */

            //driver.switchTo().frame(7); /* uncomment this line to catch the NoSuchFrameException */
        }catch (NoSuchFrameException e){
            System.out.println("No such frame ");
        }

        String btnDateCssSelector = "#datepicker";
        WebElement btnDate = driver.findElement(By.cssSelector(btnDateCssSelector));
        btnDate.click();

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Tbilisi"));
        YearMonth lastMonth = YearMonth.of(today.getYear(), today.getMonth()).minusMonths(1);
        LocalDate lastDayOfLastMonth = lastMonth.atEndOfMonth();

        // formatting date for date picker
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = dateFormatter.format(lastDayOfLastMonth);

        btnDate.sendKeys(formattedDate);

        Thread.sleep(8000);
        driver.quit();
    }
    //uncomment next line to invoke Thread TimeoutException
    @Test//(timeOut=500)
    public  void alerts() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "https://demoqa.com/alerts ";
        driver.navigate().to(mainPageUrl);

        String btnTimerAlertCssSelector = "#timerAlertButton";

        try {
            WebElement btnTimerAlert = driver.findElement(By.cssSelector(btnTimerAlertCssSelector));
            driver.navigate().refresh();// Try to invoke StaleElementReferenceException
            btnTimerAlert.click();
            //Trying to invoke TimeOutException
            WebDriverWait wait = new WebDriverWait(driver,4);
            // avoid NoAlertPresentException and Handle possible exception,comment next line to invoke NoAlertPresentException
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException e) {
            System.out.println("no alert on page");
        }
        catch (TimeoutException e){
            System.out.println("TimeOut exception");
        }catch (StaleElementReferenceException e){
            System.out.println("Stale Element Reference Exception");
        }

        Thread.sleep(7000);
        driver.quit();
    }

}
