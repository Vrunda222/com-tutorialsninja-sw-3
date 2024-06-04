package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        List<WebElement> optionList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//li/a"));
        for (WebElement e : optionList) {
            //System.out.println(e.getText());
            if (e.getText().equalsIgnoreCase(option)) {
                e.click();
                break;
            }
        }
    }


    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        //1.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        //1.3 Verify the text “Register Account”.
        Assert.assertEquals("text is not displayed", "Register Account", getTextFromElement(By.xpath("//h1[contains(text(),'Register Account')]")));
    }



    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        // 2.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        // 2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //2.3 Verify the text “Returning Customer”.
        Assert.assertEquals("text is not displayed", "Returning Customer", getTextFromElement(By.xpath("//h2[contains(text(),'Returning Customer')]")));
    }


    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        //3.1 Click  on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        // 3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
        selectMyAccountOptions("Register");
        // 3.3 Enter First Name
        sendTextToElement(By.id("input-firstname"), "Soniya");
        // 3.4 Enter Last Name
        sendTextToElement(By.id("input-lastname"), "Patel");
        // 3.5 Enter Email
        sendTextToElement(By.id("input-email"), "soniyapatel123@gmail.com");
        // 3.6 Enter Telephone
        sendTextToElement(By.name("telephone"), "9854322121");
        // 3.7 Enter Password
        sendTextToElement(By.id("input-password"), "test@123");
        // 3.8 Enter Password Confirm
        sendTextToElement(By.name("confirm"), "test@123");
        // 3.9 Select Subscribe Yes radio button
        clickOnElement(By.cssSelector("input[value='1'][name='newsletter']"));
        // 3.10 Click on Privacy Policy check box
        clickOnElement(By.name("agree"));
        // 3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@value='Continue']"));
        // 3.12 Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("text is not displayed", "Your Account Has Been Created!", getTextFromElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")));
        // 3.13 Click on Continue button
        clickOnElement(By.linkText("Continue"));
        // 3.14 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        // 3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        // 3.16 Verify the text “Account Logout”
        Assert.assertEquals("text is not displayed", "Account Logout", getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]")));
        // 3.17 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }


    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        // 4.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //  4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //  4.3 Enter Email address
        sendTextToElement(By.id("input-email"), "soniyapatel123@gmail.com");
        //  4.4 Enter Last Name  -- @there is no field 'Last Name'
        //  4.5 Enter Password
        sendTextToElement(By.id("input-password"), "test@123");
        //  4.6 Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));
        //  4.7 Verify text “My Account”
        Assert.assertEquals("text is not displayed", "My Account", getTextFromElement(By.xpath("//h2[contains(text(),'My Account')]")));
        //  4.8 Click on My Account Link.
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));
        //  4.9 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //  4.10 Verify the text “Account Logout”
        Assert.assertEquals("text is not displayed", "Account Logout", getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]")));
        //  4.11 Click on Continue button
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
