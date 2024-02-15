package testscenarios;

import browser.browserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pageElements.*;

import static Pages.OrangeHrm.clearInput;

public class OrangeHrmTest {
    static browserFactory brows = new browserFactory();
    static WebDriver driver = browserFactory.driverInitialization("chrome");
    static pageElements.login login = new login(driver);

    public static void getLogin(String username, String password) {

        By[] getLoginInput = login.getLoginInput();
        By getLoginButton = login.getLoginButton();

        WebElement uname = brows.waitForElementTObeLocated(driver, 20, getLoginInput[0]);
        clearInput(uname);
        uname.sendKeys(username);

        WebElement pswd = brows.waitForElementTObeLocated(driver, 20, getLoginInput[1]);
        clearInput(pswd);
        pswd.sendKeys(password);

        WebElement loginButton = driver.findElement(getLoginButton);
        loginButton.click();
    }

    @BeforeTest
    public void setUp() {
        driver = browserFactory.driverInitialization("chrome");
        driver.manage().window().maximize();
        login = new login(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }



    @Test(description = "Valid Login Test")
    public void validLoginTest() {
        getLogin("Admin", "admin123");
        // Assert the success of login, e.g., by checking for a dashboard element
        WebElement dashboardElement = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"));
        Assert.assertTrue(dashboardElement.isDisplayed(), "Login was  successful.");
    }



    @Test(description = "Invalid Login Test")
    public void invalidLoginTest() {
        getLogin("invalid_username", "invalid_password");
        // Assert the presence of an error message for invalid login
        WebElement errorMessageElement = driver.findElement(By.xpath("//div[@role='alert']"));
        Assert.assertTrue(errorMessageElement.isDisplayed(), "Error message not displayed for invalid login.");
    }



    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
