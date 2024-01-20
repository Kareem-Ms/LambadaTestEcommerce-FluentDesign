package tests.endToEndScenarios;

import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductDetailsPage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Epic("LambadaEcommerce")
@Feature("Login tests")
public class CheckOutTests {

    //************ Variables ************\\
    private static final ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>();
    HomePage homePage;
    ProductDetailsPage productDetailsPage;

    //*************** Tests **************\\
    @Test(description = "Verify registering new user using unregistered email")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register new user")
    public void validateRegisteringNewUser(){
        String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
        String email = testData.get().getTestData("UserInfo.email")+currentTime+testData.get().getTestData("UserInfo.domain");

        homePage.openRegisterPage()
                .registerUser(email)
                .verifyAccountCreatedSuccessfully();
    }

    @Test(dependsOnMethods = "validateRegisteringNewUser", description = "Verify searching for Product by it's name")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Search for product")
    public void validateSearchingForProduct(){
        homePage.SearchForProduct()
                .openProdctDetailsPage()
                .verifyAppearanceOfProductDetailsTitle();
    }

    @Test(dependsOnMethods = "validateSearchingForProduct", description = "Verify adding product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add product to cart")
    public void validateAddingProductToCart(){
        productDetailsPage.AddProductToCart()
                          .OpenCart()
                          .verifyProductExistsInCart();
    }

    @Test(dependsOnMethods = "validateAddingProductToCart", description = "Verify checking out some products that exist in cart")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Checkout products in cart")
    public void validateCheckingOut(){
        productDetailsPage.openCheckoutPage()
                          .addBillingAddress()
                          .confirmOrder()
                          .verifyOrderConfirmedSuccessfully();
    }

    //********** Configurations **********\\
    @BeforeTest
    public void setUp(){
        driver.set(new SHAFT.GUI.WebDriver());
        testData.set(new SHAFT.TestData.JSON("CheckOutTestData.json"));
        homePage = new HomePage(driver.get(),testData.get());
        productDetailsPage = new ProductDetailsPage(driver.get(),testData.get());
    }

    @AfterTest
    public void tearDown(){
        driver.get().quit();
    }
}
