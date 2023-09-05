package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {

    //********** Variables **********\\
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    public HomePage(SHAFT.GUI.WebDriver driver, SHAFT.TestData.JSON testData){
        this.driver = driver;
        this.testData = testData;
    }

    //********** Locators **********\\
    private By MyAccountDropdownLocator = By.xpath("//span[contains(text(),'My account')]/parent::div/parent::a[@role = 'button']");
    private By LogoutLinkLocator = By.xpath("//span[contains(text(),'Logout')]/parent::div/parent::a[contains(@href, 'logout')]");
    private By LogoutSuccessMsgLocator = By.className("page-title");

    //********** Actions **********\\
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
        driver.element()
                .hover(MyAccountDropdownLocator)
                .click(LogoutLinkLocator);
        return this;
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
