package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {

    //*********** Variables ************\\
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;

    public CheckoutPage(SHAFT.GUI.WebDriver driver, SHAFT.TestData.JSON testData){
        this.driver = driver;
        this.testData = testData;
    }

    //************* Locators ************\\
    By firstNameInputLocator = By.id("input-payment-firstname");
    By lastNameInputLocator = By.id("input-payment-lastname");
    By Address1InputLocator = By.id("input-payment-address-1");
    By CityInpuLocator = By.id("input-payment-city");
    By CountryDropDownLocator = By.id("input-payment-country");
    By RegionDroDownLocator = By.id("input-payment-zone");
    By AgreeToTermsInputLocator = By.cssSelector("label[for = 'input-agree']");
    By ContinueBtnLocator = By.id("button-save");
    By ConfirmOrderButton = By.id("button-confirm");
    By ConfirmOrderConfirmationMsg = By.className("page-title");

    //********** Single Actions **********\\
    @Step("Enter [{firstName}] into first name field")
    public void setFirstNameInput(String firstName){
        driver.element().type(firstNameInputLocator,firstName);
    }

    @Step("Enter [{lastName}] into last name field")
    public void setLastNameInput(String lastName){
        driver.element().type(lastNameInputLocator, lastName);
    }
    @Step("Enter [{address}] into Address 1 field")
    public void setAddress1Input(String address){
        driver.element().type(Address1InputLocator, address);
    }
    @Step("Enter [{city}] into city field")
    public void setCityInput(String city){
        driver.element().type(CityInpuLocator, city);
    }
    @Step("Enter [{country}] into country field")
    public void selectCountry(String country){
        driver.element().select(CountryDropDownLocator,country);
    }
    @Step("Enter [{region}] into region field")
    public void selectRegion(String region){
        driver.element().select(RegionDroDownLocator,region);
    }
    @Step("Click on Agree to terms checkbox")
    public void checkAgreeToTermsCheckBox(){
        driver.element().click(AgreeToTermsInputLocator);
    }
    @Step("Click on continue button")
    public void clickOnContinueBtn(){
        driver.element().click(ContinueBtnLocator);
    }

    //************ Main Actions ***********\\
    @Step("Add billing address")
    public CheckoutPage addBillingAddress(){
        setFirstNameInput(testData.getTestData("UserInfo.firstName"));
        setLastNameInput(testData.getTestData("UserInfo.LastName"));
        setAddress1Input(testData.getTestData("UserInfo.Address1"));
        setCityInput(testData.getTestData("UserInfo.City"));
        selectCountry(testData.getTestData("UserInfo.Country"));
        selectRegion(testData.getTestData("UserInfo.Region"));
        checkAgreeToTermsCheckBox();
        clickOnContinueBtn();
        return this;
    }

    @Step("Click on confirm order button")
    public CheckoutPage confirmOrder(){
        driver.element().click(ConfirmOrderButton);
        return this;
    }

    //************ Validations ************\\
    public void verifyOrderConfirmedSuccessfully(){
        driver.verifyThat().element(ConfirmOrderConfirmationMsg).text().equals("Your order has been placed!");
    }
}
