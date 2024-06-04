package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    /**
     * This method will click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    /**
     * This method will send text to element
     */
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }


 //************************* Alert Methods *****************************************************//
    // total 5 method need to create
    //1. switchToAlert
    //2. acceptAlert
    //3. dismissAlert
    //4. sendTextToAlert
    //5. getTextFromAlert

 //*************************** Select Class Methods ***************************************//
    // total 3 method
    //1. selectByValueFromDropDown(By by, String value)

 public void selectByValueFromDropDown(By by, String value){
     WebElement dropDown = driver.findElement(by);
     // Create the object of Select class
     Select select = new Select(dropDown);
     // Select by visible Text
     select.selectByValue(value);
 }


    //2. selectByIndexFromDropDown(By by, int index)
    public void selectByIndexFromDropDown(By by, int index){
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select class
        Select select = new Select(dropDown);
        // Select by visible Text
        select.selectByIndex(index);
    }

    //3. selectByVisibleTextFromDropDown(By by, String value)

//*************************** Mouse Hover ***************************************//


}