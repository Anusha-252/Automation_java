package pageElements;

import org.openqa.selenium.By;

public class afterAddKpiForm {

    private By tableBody = By.xpath("//div[@class='oxd-table-body']");
    private By records = By.xpath("//*[@class='oxd-table-card']");


    public  By[] getLocators(){
        return new By[]{records,tableBody};
    }



}
