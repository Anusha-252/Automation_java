package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class addKPIForm {

    WebDriver driver;
    private final By indicatorName= By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input");
    private final By jobTitle=By.xpath("//div[@class='oxd-select-text-input' and contains(text(),'Select')]");
    private final By minRating=By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private  final By maxRating=By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private final By saveButton=By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
    private final By cancelButton=By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[3]/button[1]");
    public  By selectAdmin = By.xpath("//div[@role='listbox'][1]");


    public addKPIForm(WebDriver driver){
        this.driver=driver;
    }

    public By[] getKPIsInput(){
        return new By[] {indicatorName,jobTitle,minRating,maxRating};
    }

    public By[] getKPIsButton(){
        return new By[] {saveButton,cancelButton};
    }


}








