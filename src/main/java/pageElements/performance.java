package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class performance {
    WebDriver driver;
    private final By dropdown=By.xpath("//span[contains(text(), 'Configure')]");
    private final By dropdownElement=By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='KPIs']");

    public performance(WebDriver driver) {
        this.driver = driver;
    }

    public By getConfigure(){
        return dropdown;
    }

    public  By getDropdownElement(){
        return dropdownElement;
    }
}
