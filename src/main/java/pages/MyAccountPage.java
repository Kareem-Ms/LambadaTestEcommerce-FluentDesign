package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MyAccountPage {

    //********** Variables ***********\\
    private final SHAFT.GUI.WebDriver driver;
    public MyAccountPage(SHAFT.GUI.WebDriver driver){
        this.driver = driver;
    }

    //************ Locators *************\\
    private final By MyAccountTitleLocator = By.xpath("//h2[contains(@class , 'card-header')][text() = 'My Account']");

    //********** Single Actions **********\\

    //*********** Main Actions ***********\\

    //********** Validations **********\\
    @Step("Verify that account title appears")
    public void verifyMyAccountTitleAppears(){
        driver.verifyThat()
                .element(MyAccountTitleLocator)
                .isVisible()
                .withCustomReportMessage("Account title appeard")
                .perform();
    }
}
