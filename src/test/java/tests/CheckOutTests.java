package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Epic("LambadaEcommerce")
@Feature("Login tests")
public class CheckOutTests {

    //************ Variables ************\\
    private static final ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>();

    //*************** Tests **************\\
    @Test(description = "Verify checkout functionality for specific product")
    public void verifyCheckoutSuccessfully(){
        String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
        String email = testData.get().getTestData("UserInfo.email")+currentTime+testData.get().getTestData("UserInfo.domain");

        new HomePage(driver.get(),testData.get())
                .openRegisterPage()
                .registerUser(email)
                .verifyAccountCreatedSuccessfully()
                .SearchForProduct()
                .openProdctDetailsPage()
                .verifyAppearanceOfProductDetailsTitle()
                .AddProductToCart()
                .OpenCart()
                .verifyProductExistsInCart()
                .openCheckoutPage()
                .addBillingAddress() ;
    }


    //********** Configurations **********\\
    @BeforeMethod
    public void setUp(){
        driver.set(new SHAFT.GUI.WebDriver());
        testData.set(new SHAFT.TestData.JSON("CheckOutTestData.json"));
    }

    @AfterMethod
    public void tearDown(){
        driver.get().quit();
    }
}
