package tests;

import com.shaft.driver.SHAFT;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Epic("LambadaEcommerce")
@Feature("Login tests")
public class LoginTests {

    //************ Variables ************\\
    private static final ThreadLocal<SHAFT.GUI.WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>();

    //*************** Tests **************\\

    @Test(description = "Verify login with a registered email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with registered email")
    public void verifyLoginWithRegisteredEmailAndPw(){
        String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
        String email = testData.get().getTestData("UserInfo.email")+currentTime+testData.get().getTestData("UserInfo.domain");

        new HomePage(driver.get(),testData.get())
                .openRegisterPage()
                .registerUser(email)
                .verifyAccountCreatedSuccessfully()
                .logOut()
                .verifyLogoutSuccessfully()
                .openLoginPage()
                .login(email)
                .verifyMyAccountTitleAppears();
    }

    @Test(description = "Verify login with unregistered email and password")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login with unregistered email")
    public void verifyLoginWithUnRegisteredEmailAndPw(){
        String currentTime = new SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new Date());
        String email = testData.get().getTestData("UserInfo.email")+currentTime+testData.get().getTestData("UserInfo.domain");

        new HomePage(driver.get(),testData.get())
                .openLoginPage()
                .login(email);

        //here we have to break the fluency because we login method can lead us to my account page if success and can remain at login page in case of failure
        new LoginPage(driver.get(),testData.get()).verifyLoginFaildMsgAppears();
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
