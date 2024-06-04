package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utility;

import java.time.Duration;

public class TopMenuTest extends Utility {
    static String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu){
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='"+menu+"']"));
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() throws InterruptedException {
        //1.1 Mouse hover on “Desktops” Tab and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Desktops']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllDesktops']")));
        jacketsOption.click();
        Thread.sleep(5000);
        //1.3 Verify the text ‘Desktops’
        Assert.assertEquals("Desktops", getTextFromElement(By.xpath("//h2[normalize-space()='Desktops']")));
    }
    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Laptops & Notebooks']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']")));
        jacketsOption.click();
        Thread.sleep(5000);
        //2.3 Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals("Laptops & Notebooks", getTextFromElement(By.xpath("//h2[normalize-space()='Laptops & Notebooks']")));
    }
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() throws InterruptedException {
        //3.1 Mouse hover on “Components” Tab and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Components']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllComponents']")));
        jacketsOption.click();
        Thread.sleep(5000);
        //3.3 Verify the text ‘Components’
        Assert.assertEquals("Components", getTextFromElement(By.xpath("//h2[normalize-space()='Components']")));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
