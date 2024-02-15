package browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.lang.System.exit;

public class browserFactory {
    public static WebDriver driver;
    public static WebDriver driverInitialization(String browserName) {
        switch(browserName.toLowerCase()){
            case "chrome":driver =new ChromeDriver();
                break;
            case "edge":driver =new EdgeDriver();
                break;
            case "firefox":driver =new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name" +browserName);
                exit(1);
        }return driver;
    }

    public  Wait<WebDriver> genericWait(long time, WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public  WebElement waitForElementTObeLocated(WebDriver driver, long time, By element){
        Wait<WebDriver> wait= genericWait(time, driver);
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(element));//WebElement

    }
    public  WebElement waitForElementTObeInvisible(WebDriver driver, long time, By element){
        Wait<WebDriver> wait= genericWait(time, driver);
        return wait.until(ExpectedConditions.elementToBeClickable(element));

    }


}
