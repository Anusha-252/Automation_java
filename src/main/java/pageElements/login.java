package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class login {
    WebDriver driver;
    private final By username = By.xpath("//input[@name='username']");
    private final By password = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@type='submit']");


    public login(WebDriver driver) {
        this.driver=driver;
    }

    public By[] getLoginInput(){
        return new By[] {username,password};
    }
    public By getLoginButton(){
        return loginButton;
    }




}
