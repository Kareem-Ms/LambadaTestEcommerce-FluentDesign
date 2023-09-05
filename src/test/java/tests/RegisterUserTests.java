package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Epic("LambadaEcommerce")
@Feature("Register tests")
public class RegisterUserTests {

    //********** Variables **********\\
    private static final ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>();
    String email;
    String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());

    //********** Tests **********\\
    @Test(description = "Verify registering new user")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Register new user")
    public void VerifyRegisteringNewUser(){
        email = testData.get().getTestData("UserInfo.email")+currentTime+testData.get().getTestData("UserInfo.domain");

        new HomePage(driver.get(), testData.get())
                .openRegisterPage()
                .registerUser(email)
                .verifyAccountCreatedSuccessfully();
    }

    @Test(dependsOnMethods = "VerifyRegisteringNewUser", description = "Verify registering an existing user")
    @Severity(SeverityLevel.NORMAL)
    @Story("Register an existing user")
    public void VerifyRegisteringExistingUser(){
        new HomePage(driver.get(), testData.get())
                .openRegisterPage()
                .registerUser(email)
                .verifyEmailIsAlreadyRegistered();
    }

    //********** Configurations **********\\
    @BeforeMethod
    public void setUp(){
        driver.set(new SHAFT.GUI.WebDriver());
        testData.set(new SHAFT.TestData.JSON("RegisterUserTestData.json"));
    }

    @AfterMethod
    public void tearDown(){
        driver.get().quit();
    }

}
