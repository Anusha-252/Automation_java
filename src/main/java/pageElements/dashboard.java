package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class dashboard {
    WebDriver driver;
    private final By actionlinks=By.xpath("//p[text()='(1) Pending Self Review']");

    public dashboard(WebDriver driver){
        this.driver=driver;
    }

    public By getActionLink(){
        return  actionlinks;
    }

}
