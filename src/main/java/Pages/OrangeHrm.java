package Pages;

import browser.browserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import pageElements.*;

import java.util.List;


public class OrangeHrm {
    static Logger log = LogManager.getLogger("OrangeHrm dashboard automation");
    static browserFactory brows = new browserFactory();
    static WebDriver driver = browserFactory.driverInitialization("chrome");
    static login login = new login(driver);
    static dashboard dashboard = new dashboard(driver);
    static performance performance = new performance(driver);
    static KPIsSettings kpisSettings = new KPIsSettings(driver);
    static addKPIForm addKPIForm = new addKPIForm(driver);
    static afterAddKpiForm kpi = new afterAddKpiForm();

    public static void main(String[] args) {
        try {

            driver.manage().window().maximize();
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            /*Test case for login*/
            getLogin("", "");
            log.info("Blank Username and password");
            getLogin("Admin", "");
            log.info("Blank password");
            getLogin("", "admin123");
            log.info("Blank username");
            getLogin("asfscf", "sfdgfds34");
            log.info("Invalid username and password");
            getLogin("Admin", "admin123");
            log.info("Valid username and password");
            log.info("Successfully Logged in!!");

            getActionLink();
            getConfigureMenu();
            getKpisSettingsAction();
            fillAndCheckKPIForm("Anusha", "6", "100");


            // Get the count of table records after adding KPIs
            int recordCount = getTableDataCount();
            System.out.println("Total number of record  is\t" + recordCount);

            searchKpiData();
            int recordCountAfterSearch = getTableDataCount();
            System.out.println("Number of searched record  is\t" + recordCountAfterSearch);


        } catch (ElementNotInteractableException | NoAlertPresentException e) {
            System.out.println("Error:" + e);
        }
    }


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


    public static void clearInput(WebElement inputField) {
        inputField.sendKeys(Keys.CONTROL + "a"); // Select all text in the input field
        inputField.sendKeys(Keys.BACK_SPACE);
    }

    public static void getActionLink() {

        By getActionLink = dashboard.getActionLink();
        WebElement ActionlinkClick = brows.waitForElementTObeLocated(driver, 20, getActionLink);
        ActionlinkClick.click();
        log.info("Click on (1) Pending Self Review of My Action Div");
        log.info("Landed to Performance/Manage review page");

    }

    public static void getConfigureMenu() {

        By getConfigureMenu = performance.getConfigure();
        WebElement configureMenuClick = brows.waitForElementTObeLocated(driver, 20, getConfigureMenu);
        configureMenuClick.click();
        log.info("Click on Configure And Dropdown opened.");

        By dropdownElementLocator = performance.getDropdownElement();
        WebElement dropdownList = brows.waitForElementTObeLocated(driver, 20, dropdownElementLocator);
        dropdownList.click();
        log.info("Select Kpi menu And clicked");
        log.info("Performance/Configure page is opened");

    }

    public static void getKpisSettingsAction() {

        By addLocator = kpisSettings.clickAddButton();
        WebElement addButton = brows.waitForElementTObeLocated(driver, 20, addLocator);
        addButton.click();
        log.info("Click on add button");
        log.info("KPI form opened");

    }

    public static void getAddKPIsForm(String indicatorName, String minRating, String maxRating) {

        By[] getKPIsInput = addKPIForm.getKPIsInput();

        WebElement indicName = brows.waitForElementTObeLocated(driver, 20, getKPIsInput[0]);
        indicName.clear();
        indicName.sendKeys(indicatorName);

        WebElement jbTitle = brows.waitForElementTObeLocated(driver, 50, getKPIsInput[1]);
        jbTitle.click();

        WebElement selectJobTitle = brows.waitForElementTObeInvisible(driver, 40, addKPIForm.selectAdmin);
        selectJobTitle.click();

        WebElement minRate = brows.waitForElementTObeLocated(driver, 20, getKPIsInput[2]);
        minRate.clear();
        minRate.sendKeys(minRating);

        WebElement maxRate = brows.waitForElementTObeLocated(driver, 20, getKPIsInput[3]);
        maxRate.sendKeys(Keys.CONTROL, ("a"), Keys.DELETE);
        maxRate.sendKeys(maxRating);


    }

    public static void fillAndCheckKPIForm(String indicatorName, String minRating, String maxRating) {
        // Fill and save the KPI form
        getAddKPIsForm(indicatorName, minRating, maxRating);
        log.info("Filled up KPI form");

        // Click the Cancel button to check its functionality
        By cancelButtonLocator = addKPIForm.getKPIsButton()[1];
        WebElement cancelButton = driver.findElement(cancelButtonLocator);
        cancelButton.click();
        log.info("Clicked on cancel button to check its functionality");
        log.info("Back to Performance/Configure page");


        // Now click the Add button to open a new form
        getKpisSettingsAction();
        log.info("Clicked on add button again");


        // Fill and save the KPI form again
        getAddKPIsForm("Sai", "4", "100");
        log.info("Filled up KPI form");

        By saveButttonLocator = addKPIForm.getKPIsButton()[0];
        WebElement saveButton = driver.findElement(saveButttonLocator);
        saveButton.click();
        log.info("Clicked in save button");
        log.info("Form filled up successfully!!");
    }

    public static void searchKpiData(){
        By[] getKPIsInput = addKPIForm.getKPIsInput();
        WebElement jbTitle1 = brows.waitForElementTObeLocated(driver, 20, getKPIsInput[1]);
        jbTitle1.click();
        log.info("Select job title to search");

        WebElement selectJobTitle1 = brows.waitForElementTObeInvisible(driver, 20, addKPIForm.selectAdmin);
        selectJobTitle1.click();
        By searchLocator = kpisSettings.clickSearchButton();
        brows.waitForElementTObeLocated(driver, 20, searchLocator).click();
        log.info("Clicked on search button");


    }


    public static int getTableDataCount() {

        By[] getLocators = kpi.getLocators();

        // Find the table body element
        WebElement tableBodyElement = brows.waitForElementTObeLocated(driver, 20, getLocators[1]);

        // Find all the rows within the table
        List<WebElement> rows = tableBodyElement.findElements(getLocators[0]);

        // Return the count of rows (records) in the table
        return rows.size();

    }

}
