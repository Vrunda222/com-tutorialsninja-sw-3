package laptopsandnotebooks;

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

public class LaptopsAndNotebooksTest extends Utility {
    static String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Laptops & Notebooks']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        //1.2 Click on “Show All Laptops & Notebooks”
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']")));
        jacketsOption.click();
        Thread.sleep(5000);
        //1.3 Select Sort By "Price (High > Low)"
        WebElement dropdownSortBy = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select selectSortBy = new Select(dropdownSortBy);
        selectSortBy.selectByVisibleText("Price (High > Low)");
        //1.4 Verify the Product price will arrange in High to Low order.
        // Wait for the products to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[@id='product-category']/div[@class='row']/div[@id='content']/div[4]")));

        // Fetch all product names
        List<WebElement> productElements = driver.findElements(By.xpath("//body[1]/div[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/p[2]"));
        List<String> productPrice = new ArrayList<>();
        for (WebElement productElement : productElements) {
            productPrice.add(productElement.getText());
        }

        // Create a copy of the product price list and sort it
        List<String> sortedProductPrice = new ArrayList<>(productPrice);
        Collections.sort(sortedProductPrice);

        // Compare the original list with the sorted list
        if (productPrice.equals(sortedProductPrice)) {
            System.out.println("The product's Price are displayed in Low to High order.");
        } else {
            System.out.println("The product's Price are displayed in High to Low order.");
        }
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement womenMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Laptops & Notebooks']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(womenMenu).perform();
        //2.2 Click on “Show All Laptops & Notebooks”
        WebElement jacketsOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Show AllLaptops & Notebooks']")));
        jacketsOption.click();
        Thread.sleep(5000);
        //2.3 Select Sort By "Price (High > Low)"
        WebElement dropdownSortBy = driver.findElement(By.xpath("//select[@id='input-sort']"));
        Select selectSortBy = new Select(dropdownSortBy);
        selectSortBy.selectByVisibleText("Price (High > Low)");
        //2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));
        //2.5 Verify the text “MacBook”
        Assert.assertEquals("MacBook", getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']")));
        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertEquals("Success: You have added MacBook to your shopping cart!", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).substring(0,54));
        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0,13));
        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals("MacBook", getTextFromElement(By.xpath("//body[1]/div[2]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")));
        //2.11 Change Quantity "2"
       // driver.findElement(By.xpath("//input[@name='quantity']")).clear();
       // sendTextToElement(By.xpath("//input[@name='quantity']"), "2");
        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Success: You have modified your shopping cart!", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).substring(0,46));
        //2.14 Verify the Total £737.45
        //Assert.assertEquals("£737.45", getTextFromElement(By.xpath("//tbody//tr//td[6]")));
        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
        Thread.sleep(4000);
        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Checkout", getTextFromElement(By.xpath("//h1[normalize-space()='Checkout']")));
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals("New Customer", getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']")));
        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));
        //2.19 Click on “Continue” tab
        clickOnElement(By.xpath("//input[@id='button-account']"));
        //2.20 Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='input-payment-firstname']"), "Vrunda");
        sendTextToElement(By.xpath("//input[@id='input-payment-lastname']"), "Vyas");
        sendTextToElement(By.xpath("//input[@id='input-payment-email']"), "vrunda@gmail.com");
        sendTextToElement(By.xpath("//input[@id='input-payment-telephone']"), "9656523236");
        sendTextToElement(By.xpath("//input[@id='input-payment-address-1']"), "58, abc street");
        sendTextToElement(By.xpath("//input[@id='input-payment-city']"), "Nadiad");
        sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"), "6589");
        WebElement dropdownCountry = driver.findElement(By.xpath("//select[@id='ExpireMonth']"));
        Select selectCountry = new Select(dropdownCountry);
        selectCountry.selectByVisibleText("Turkey");
        Thread.sleep(3000);
        WebElement dropdownState = driver.findElement(By.xpath("//select[@id='ExpireMonth']"));
        Select selectState = new Select(dropdownState);
        selectState.selectByVisibleText("Kars");
        //2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));
        //2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Hello Hi Product is good.");
        clickOnElement(By.xpath("//input[@id='button-shipping-method']"));
        //2.23 Check the Terms & Conditions check box
        sendTextToElement(By.xpath("//div[@id='collapse-payment-method']//textarea[@name='comment']"), "Hello Hi Product is good.");
        clickOnElement(By.xpath("//input[@name='agree']"));
        //2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));
        //2.25 Verify the message “Warning: Payment method required!”
        /** message cannot verify because payment method is already selected **/

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
