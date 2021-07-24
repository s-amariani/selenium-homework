import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WebElementsTest {

    @Test
    public void AddAndRemoveElements() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //opening web-page
        String mainWebPageUrl = "http://the-internet.herokuapp.com/add_remove_elements/";
        driver.navigate().to(mainWebPageUrl);

        WebElement btnAddElement = driver.findElement(By.cssSelector("button[onclick='addElement()']"));
        //clicking on button "add element" three times
        for (int i =0;i<3;i++)
            btnAddElement.click();

        //Printing the last 'Delete' button element with findElement()
        WebElement btnDeleteLast = driver.findElement(By.cssSelector("#elements > button.added-manually:last-child"));
        System.out.println(btnDeleteLast);

        //Printing the last 'Delete' button element with findElements().Using cssSelector as locator of element with class name , that starts with 'added' word
        List<WebElement> btnDeleteLast_2 = driver.findElements(By.cssSelector("[class^='added']:last-child"));
        System.out.println(btnDeleteLast_2);
        // get text from findElements()
        List<String> a = new ArrayList<>();

        for (int i = 0; i < btnDeleteLast_2.size(); i++) {
            a.add(btnDeleteLast_2.get(i).getText());
            System.out.println("List: "+btnDeleteLast_2.get(i).getText());
        }

    ////////////////////////

        //Printing the last 'Delete' button element with findElement().Using relative XPath with tag, that can accept any char with class name that contains 'manually' and text() 'Delete'
        WebElement btnDeleteLast_3 = driver.findElement(By.xpath("//button[last()][contains(@class,'manually') and contains(text(),'Delete')]"));
        System.out.println(btnDeleteLast_3);

        driver.quit();
    }

    @Test
    public void ChallengingDom() throws MalformedURLException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //opening web-page
        driver.navigate().to("http://the-internet.herokuapp.com/challenging_dom");

        //Using relative XPath, Print out the Lorem value of element, that has 'Apeirian9' as Ipsum value
        WebElement loremValue = driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'Iuvaret9')]"));
        System.out.println("The Lorem value of element, that has 'Apeirian9' as Ipsum value is: "+loremValue.getText());

        //Using relative XPath, Print out the next element of element with Ipsum value 'Apeirian9'
        WebElement followingValue = driver.findElement(By.xpath("//tbody//tr//td[contains(text(),'Apeirian9')]/following-sibling::td"));
        System.out.println("The next element of element with Ipsum value 'Apeirian9' is: "+followingValue.getText());

        driver.quit();
    }
}
