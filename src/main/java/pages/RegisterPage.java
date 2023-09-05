package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegisterPage {

    //********** Variables **********\\
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    public RegisterPage(SHAFT.GUI.WebDriver driver , SHAFT.TestData.JSON testData){
        this.driver = driver;
        this.testData = testData;
    }


    //********** Locators **********\\
    private final By FirstNameInputLocator = new By.ById("input-firstname");
    private final By LastNameInputLocator = new By.ById("input-lastname");
    private final By EmailInputLocator = new By.ById("input-email");
    private final By TelephoneNumberInputLocator = new By.ById("input-telephone");
    private final By PasswordInputLocator = new By.ById("input-password");
    private final By ConfirmPasswordInputLocator = new By.ById("input-confirm");
    private final By NewsLetterYesRadioBtnLocator = new By.ByXPath("//label[@for = 'input-newsletter-yes']");
    private final By ContinueBtnLocator = new By.ByCssSelector("input[value = 'Continue']");
    private final By AgreeTermsCheckboxLocator = new By.ByXPath("//label[@for = 'input-agree']");
    private final By AccountCreatedSuccessMsgLocator = By.className("page-title");
    private final By EmailExistBeforeErrorMsgLocator = By.className("alert-danger");

    //********** Single Actions **********\\
    @Step("Enter [{firstName}] in the first name input field")
    public void setFirstName(String firstName) {
        driver.element().type(FirstNameInputLocator, firstName);
    }
    @Step("Enter [{lastName}] in the last name input field")
    public void setLastName(String lastName) {
        driver.element().type(LastNameInputLocator, lastName);
    }
    @Step("Enter [{email}] in the last email input field")
    public void setEmailInput(String email) {
        driver.element().type(EmailInputLocator, email);
    }
    @Step("Enter [{telephoneNumber}] in the last phone number input field")
    public void setTelephoneNumber(String telephoneNumber) {
        driver.element().type(TelephoneNumberInputLocator, telephoneNumber);
    }

    @Step("Enter [{password}] in the last password input field")
    public void setPassword(String password) {
        driver.element().type(PasswordInputLocator, password);
    }
    @Step("Enter [{confirmPassword}] in the confirm password input field")
    public void setConfirmPassword(String confirmPassword) {
        driver.element().type(ConfirmPasswordInputLocator, confirmPassword);
    }

    @Step("Click on yes to subscribe news letter radio button")
    public void clickNewsLetterYesRadioBtn() {
        driver.element().click(NewsLetterYesRadioBtnLocator);
    }
    @Step("Click on continue button")
    public void clickContiuneBtn() {
        driver.element().click(ContinueBtnLocator);
    }
    @Step("Mark agree terms checkbox")
    public void clickAgreeTermsCheckbox(){
        driver.element().click(AgreeTermsCheckboxLocator);
    }

    //********** Main Actions **********\\
    @Step("Register new user")
   public RegisterPage registerUser(String email){
        setFirstName(testData.getTestData("UserInfo.firstName"));
        setLastName(testData.getTestData("UserInfo.LastName"));
        setEmailInput(email);
        setTelephoneNumber(testData.getTestData("UserInfo.phoneNumber"));
        setPassword(testData.getTestData("UserInfo.password"));
        setConfirmPassword(testData.getTestData("UserInfo.password"));
        clickNewsLetterYesRadioBtn();
        clickAgreeTermsCheckbox();
        clickContiuneBtn();
        return this;
   }

    //********** Validations **********\\
    @Step("Verify that Account created successfully message appears")
   public HomePage verifyAccountCreatedSuccessfully(){
        driver.verifyThat().element(AccountCreatedSuccessMsgLocator).text().contains(testData.getTestData("messages.SuccessfullRegistration")).perform();
        return new HomePage(driver,testData);
   }
    @Step("Verify that email already registered error message appears")
    public void verifyEmailIsAlreadyRegistered(){
        driver.verifyThat().element(EmailExistBeforeErrorMsgLocator).text().contains(testData.getTestData("messages.EmailTakenErrorMsg")).perform();
    }


}
