import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;



public class WebFormsTest {
    @Test
    public void DropdownCheckboxesRadioButtons() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String mainPageUrl = "http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html";
        driver.navigate().to(mainPageUrl);

        String dropDownMenuCssSelector = "#dropdowm-menu-1";
        WebElement dropDownMenu = driver.findElement(By.cssSelector(dropDownMenuCssSelector));

        //Choosing programming language from dropdown menu
        Select select = new Select(dropDownMenu);
        select.selectByValue("python");

        // ensure that sql was selected
        String activeSelectedOption =  select.getFirstSelectedOption().getText();
        String expectedSelectedOption = "Python";
        Assert.assertEquals(activeSelectedOption,expectedSelectedOption);

        //Clicking on all unselected checkboxes
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector("#checkboxes > label > input[type='checkbox']"));

        for (int i = 0; i < checkBoxes.size(); i++){
            if (!checkBoxes.get(i).isSelected()){
                checkBoxes.get(i).click();
                System.out.println("checkbox "+ checkBoxes.get(i).getAttribute("defaultValue") + " is selected");
            }
            else
                System.out.println("checkbox "+ checkBoxes.get(i).getAttribute("defaultValue") + " is selected by default");

        }

        // Clicking on 'Yellow' radio button
        WebElement radioBtnYellow= driver.findElement(By.xpath("//*[@id='radio-buttons']//input[@value='yellow']"));
        radioBtnYellow.click();

        //In 'Selected & Disabled' section checking that 'Orange' option in dropdown is disabled
        WebElement selectedOptionOrange = driver.findElement(By.xpath("//*[@value='orange' and @disabled='disabled']"));
        boolean boolActualOptionOrangeEnabled = selectedOptionOrange.isEnabled();

        Assert.assertEquals(boolActualOptionOrangeEnabled, false);

        Thread.sleep(8000);
        driver.quit();
    }
}
