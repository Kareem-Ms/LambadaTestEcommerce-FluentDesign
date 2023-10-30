package pages;

import com.shaft.driver.SHAFT;
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

    //********** Single Actions **********\\
    public void setFirstNameInput(){
        driver.element().type(firstNameInputLocator,"Ahmed");
    }

    public void setLastNameInput(){
        driver.element().type(lastNameInputLocator, "mohamed");
    }

    public void setAddress1Input(){
        driver.element().type(Address1InputLocator, "Address 1");
    }

    public void setCityInput(){
        driver.element().type(CityInpuLocator, "Cairo");
    }

    public void selectCountry(){
        driver.element().select(CountryDropDownLocator,"Egypt");
    }

    public void selectRegion(){
        driver.element().select(RegionDroDownLocator,"Ad Daqahliyah");
    }

    public void checkAgreeToTermsCheckBox(){
        driver.element().click(AgreeToTermsInputLocator);
    }

    public void clickOnContinueBtn(){
        driver.element().click(ContinueBtnLocator);
    }

    //************ Main Actions ***********\\
    public CheckoutPage addBillingAddress(){
        setFirstNameInput();
        setLastNameInput();
        setAddress1Input();
        setCityInput();
        selectCountry();
        selectRegion();
        checkAgreeToTermsCheckBox();
        clickOnContinueBtn();
        return this;
    }

    //************ Validations ************\\
}
