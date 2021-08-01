import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete {

    @Test
    public void autoComplete() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "https://www.google.com/";
        driver.navigate().to(mainPageUrl);

        //Type Automation in search box
        String  inputCssSelector = ".gLFyf.gsfi";
        WebElement input = driver.findElement(By.cssSelector(inputCssSelector));
        input.sendKeys("Automation");

        // Put all the suggestions in the list
        String suggestionsCssSelector = ".erkvQe[role='listbox']";
        List<WebElement> suggestionsResult = driver.findElements(By.cssSelector(suggestionsCssSelector));
        List<String> suggestions = new ArrayList<>();

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(suggestionsCssSelector)));

        for (int i = 0;i<suggestionsResult.size();i++){
            suggestions.add(suggestionsResult.get(i).getText());
            System.out.println("list[" + i + "] " + suggestionsResult.get(i).getText());
        }

        //Click on last suggestion
        String lastSuggestionCssSelector = ".erkvQe[role='listbox'] > li:last-child";
        WebElement lastSuggestion = driver.findElement(By.cssSelector(lastSuggestionCssSelector));
        lastSuggestion.click();


        Thread.sleep(5000);
        driver.quit();
    }
}
