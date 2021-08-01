import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class FileUploadTest {

    @Test
    public void FileUpload() throws InterruptedException {

        // WebDriverManager.chromedriver().setup();
        //WebDriver driver = new ChromeDriver();

        WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME,true); //Run  test in headless mode

        String mainPageUrl = "http://the-internet.herokuapp.com/upload";
        driver.navigate().to(mainPageUrl);

        String btnChooseFileXpath = "//*[@id='file-upload']";
        WebElement btnChooseFile = driver.findElement(By.xpath(btnChooseFileXpath));

        btnChooseFile.sendKeys("C:\\Users\\Sergi\\Desktop\\test_upload_file.txt");

        String  btnUploadXpath = "//*[@id='file-submit']";
        WebElement btnUpload = driver.findElement(By.xpath(btnUploadXpath));
        try {
            driver.navigate().refresh();//Try to invoke StaleElementReferenceException exception
            btnUpload.click();
        }catch (StaleElementReferenceException e){
            System.out.println("StaleElementReferenceException");
        }


        Thread.sleep(2000);
        driver.quit();
    }

}
