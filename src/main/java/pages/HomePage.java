package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {

    //********** Variables **********\\
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    public HomePage(SHAFT.GUI.WebDriver driver, SHAFT.TestData.JSON testData){
        this.driver = driver;
        this.testData = testData;
    }

    //********** Locators **********\\
    private final By MyAccountDropdownLocator = By.xpath("//span[contains(text(),'My account')]/parent::div/parent::a[@role = 'button']");
    private final By LogoutLinkLocator = By.xpath("//span[contains(text(),'Logout')]/parent::div/parent::a[contains(@href, 'logout')]");
    private final By LogoutSuccessMsgLocator = By.className("page-title");
    private final By SearchBarLocator = By.cssSelector("div[data-id = '217822'] > div > form > div > div > div > div.flex-fill > input");

    //********** Single Actions **********\\
    @Step("Hover on my account dropdown menu")
    public void hovreOnMyAccountDropDown(){
        driver.element().hover(MyAccountDropdownLocator);
    }

    @Step("Click on logout button")
    public void clickOnLogoutButton(){
        driver.element().click(LogoutLinkLocator);
    }

    @Step("Enter [{ProductName}] into search bar")
    public void setSearchBarInput(String ProductName){
        driver.element().type(SearchBarLocator, ProductName);
    }
    @Step("Click on product name from searching dropdown list in order to open product details page")
    public void clickOnProductNameFromSearchDropDown(String ProductName){
        String ProductXpath = "(//li[contains(@class,'product-thumb')]//div[contains(@class, 'caption ')]//h4[@class = 'title']//a[text() = '"+ProductName+"'])[1]";
        driver.element().click(By.xpath(ProductXpath));
    }

    //*********** Main Actions **********\\
    @Step("Open register page")
    public RegisterPage openRegisterPage(){
        driver.browser().navigateToURL(System.getProperty("nopCommerce.baseuri")+"/index.php?route=account/register");
        return new RegisterPage(driver, testData);
    }
    @Step("Open login page")
    public LoginPage openLoginPage(){
        driver.browser().navigateToURL(System.getProperty("nopCommerce.baseuri")+"/index.php?route=account/login");
        return new LoginPage(driver, testData);
    }

    @Step("Logout from account")
    public HomePage logOut(){
        hovreOnMyAccountDropDown();
        clickOnLogoutButton();
        return this;
    }

    @Step("Search for product by it's name")
    public HomePage SearchForProduct(){
        setSearchBarInput(testData.getTestData("UserInfo.Product_Name"));
        return this;
    }

    @Step("Open product details page")
    public ProductDetailsPage openProdctDetailsPage(){
        clickOnProductNameFromSearchDropDown(testData.getTestData("UserInfo.Product_Name"));
        return new ProductDetailsPage(driver,testData);
    }

    //********** Validations **********\\
    @Step("Verify that user has been logged out message appears")
    public HomePage verifyLogoutSuccessfully(){
        driver.element()
                .verifyThat(LogoutSuccessMsgLocator)
                .isVisible()
                .withCustomReportMessage("User has been logged out successfully")
                .perform();

        return this;
    }
}
