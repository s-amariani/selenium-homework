import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WaitsTest {

    @Test
    public void ProgressBar() throws MalformedURLException, Throwable, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "https://demoqa.com/progress-bar";
        driver.navigate().to(mainPageUrl);

        String btnStartStopId = "startStopButton";
        String progressBarCssSelector = ".progress-bar.bg-info[role]";

        WebElement btnStartStop = driver.findElement(By.id(btnStartStopId));
        WebElement progressBar = driver.findElement(By.cssSelector(progressBarCssSelector));

        //clicking on start button
        btnStartStop.click();
        //waiting until progress bar value equals 100%
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.attributeToBe(progressBar,"ariaValueNow","100"));

        //printing 100%
        System.out.println(progressBar.getAttribute("ariaValueNow")+ "%");

        //thread sleep for not closing browser immediately
        Thread.sleep(2500);

        driver.quit();
    }
}
