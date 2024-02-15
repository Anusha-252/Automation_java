package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KPIsSettings {

    WebDriver driver;
    private final By addButton= By.xpath("//i[@class='oxd-icon bi-plus oxd-button-icon']");
    private final By searchButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
    private final By resetButton=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']");

    public KPIsSettings(WebDriver driver){
        this.driver=driver;
    }

    public By clickAddButton(){
        return addButton;
    }

    public By clickSearchButton(){
        return searchButton;
    }

    public By clickResetButton(){
        return resetButton;
    }

}

