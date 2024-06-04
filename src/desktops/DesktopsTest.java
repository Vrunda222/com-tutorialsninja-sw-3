package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    static String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {
        //1.1 Mouse hover on Desktops Tab.and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Desktops']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        //1.2 Click on “Show All Desktops”
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllDesktops']")));
        jacketsOption.click();
        Thread.sleep(5000);
        //1.3 Select Sort By position "Name: Z to A"
        WebElement dropdownSortBy = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select selectSortBy = new Select(dropdownSortBy);
        selectSortBy.selectByVisibleText("Name (Z - A)");
        //1.4 Verify the Product will arrange in Descending order.

        // Wait for the products to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[@id='product-category']/div[@class='row']/div[@id='content']/div[1]")));

        // Fetch all product names
        List<WebElement> productElements = driver.findElements(By.xpath("//div[@class='product-thumb']//h4//a"));
        List<String> productNames = new ArrayList<>();
        for (WebElement productElement : productElements) {
            productNames.add(productElement.getText());
        }

        // Create a copy of the product names list and sort it
        List<String> sortedProductNames = new ArrayList<>(productNames);
        Collections.sort(sortedProductNames);

        // Compare the original list with the sorted list
        if (productNames.equals(sortedProductNames)) {
            System.out.println("The products are displayed in ascending order.");
        } else {
            System.out.println("The products are displayed in descending order.");
        }
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1 Mouse hover on Currency Dropdown and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Currency']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        womenMenu.click();
        //2.2 Mouse hover on £Pound Sterling and click
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-caret-down']")));
        jacketsOption.click();
        Thread.sleep(2000);
        //2.3 Mouse hover on Desktops Tab.
        WebElement womenMen = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Desktops']")));
        Actions action = new Actions(driver);
        action.moveToElement(womenMen).perform();
        //2.4 Click on “Show All Desktops”
        WebElement jacketsOptio = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllDesktops']")));
        jacketsOptio.click();
        Thread.sleep(4000);
        //2.5 Select Sort By position "Name: A to Z"
        WebElement dropdownSortBy = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select selectSortBy = new Select(dropdownSortBy);
        selectSortBy.selectByVisibleText("Name (A - Z)");
        Thread.sleep(4000);
        //2.6 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
        //2.7 Verify the Text "HP LP3065"
        Assert.assertEquals("HP LP3065", getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']")));
        //2.8 Select Delivery Date "2023-11-27"

        //2.9.Enter Qty "1” using Select class.
        driver.findElement(By.xpath("//input[@id='input-quantity']")).clear();
        sendTextToElement(By.xpath("//input[@id='input-quantity']"), "1");
        //2.10 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        Thread.sleep(4000);
        //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Assert.assertEquals("Success: You have added HP LP3065 to your shopping cart!", driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText().substring(0,56));
        //2.12 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        //2.13 Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0,13));
        //2.14 Verify the Product name "HP LP3065"
        Assert.assertEquals("HP LP3065", getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")));
        //2.15 Verify the Delivery Date "2023-11-27"

        //2.16 Verify the Model "Product21"
        Assert.assertEquals("Product21", getTextFromElement(By.xpath("//td[normalize-space()='Product 21']")));
        //2.17 Verify the Total "£74.73"
        Assert.assertEquals("£74.73", getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[6]")));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
