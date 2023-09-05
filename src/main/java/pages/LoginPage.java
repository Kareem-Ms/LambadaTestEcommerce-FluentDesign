package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {

    //********** Variables ***********\\
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    public LoginPage(SHAFT.GUI.WebDriver driver , SHAFT.TestData.JSON testData){
        this.driver = driver;
        this.testData = testData;
    }

    //************ Locators *************\\
    public final By EmailInputLocator = new By.ById("input-email");
    private final By PasswordInputLocator = new By.ById("input-password");
    private final By LoginBtnLocator = By.cssSelector("input[value = 'Login']");
    private final By LoginFaildErrorMsgLocator = By.className("alert-danger");

    //********** Single Actions **********\\
    @Step("Enter email in the email input field")
    public void setEmail(String email) {
        driver.element().type(EmailInputLocator,email);
    }

    @Step("Enter password in the password input field")
    public void setPassword(String password) {
        driver.element().type(PasswordInputLocator, password);
    }
    @Step("Click login button")
    public void clickLoginBtn() {
        driver.element().click(LoginBtnLocator);
    }

    //*********** Main Actions ***********\\
    @Step("Login to email")
    public MyAccountPage login(String email){
        setEmail(email);
        setPassword(testData.getTestData("UserInfo.password"));
        clickLoginBtn();
        return new MyAccountPage(driver);
    }

    //********** Validations **********\\
    @Step("Verify that login fail message appears")
    public void verifyLoginFaildMsgAppears(){
        driver.element()
                .verifyThat(LoginFaildErrorMsgLocator)
                .isVisible()
                .withCustomReportMessage("Login faild message appears")
                .perform();
    }
}
