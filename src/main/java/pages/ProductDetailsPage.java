package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class ProductDetailsPage {

    //*********** Variables ************\\
    private final SHAFT.GUI.WebDriver driver;
    private final SHAFT.TestData.JSON testData;
    private final String ProductName ;

    public ProductDetailsPage(SHAFT.GUI.WebDriver driver, SHAFT.TestData.JSON testData){
        this.driver = driver;
        this.testData = testData;
        ProductName = testData.getTestData("UserInfo.Product_Name");
    }

    //************* Locators ************\\
    By AddToCartBtnLocator = By.cssSelector("#entry_216842>button[title = 'Add to Cart']");
    By CartIconLocator = By.cssSelector("#entry_217825>a");
    By CheckoutBtnInRightMenuLocator = By.xpath("//a[contains(@href , 'checkout/checkout') and contains(@class ,'icon-right')]");

    //********** Single Actions **********\\
    public By getProductTitle(){
        String ProductTitleXpath = "//h1[text() = '"+ProductName+"']";
        return By.xpath(ProductTitleXpath);
    }

    public void ClickOnAddToCartBtn(){
        driver.element().click(AddToCartBtnLocator);
    }

    public void ClickOnCartIcon(){
        driver.element().click(CartIconLocator);
    }

    public void ClickOnDismissCartBtn(){
        driver.element().click(By.xpath("//button [@data-dismiss = 'toast']"));
    }

    //************ Main Actions ***********\\
    public ProductDetailsPage AddProductToCart(){
        ClickOnAddToCartBtn();
        ClickOnDismissCartBtn();
        return this;
    }

    public CheckoutPage openCheckoutPage(){
        driver.element().click(CheckoutBtnInRightMenuLocator);
        return new CheckoutPage(driver, testData);
    }

    public ProductDetailsPage OpenCart(){
        ClickOnCartIcon();
        return this;
    }

    //************ Validations ************\\
    public ProductDetailsPage verifyAppearanceOfProductDetailsTitle(){
        driver.assertThat().element(getProductTitle()).isVisible().perform();
        return this;
    }

    public ProductDetailsPage verifyProductExistsInCart(){
        String PrdocutTitleInCartXpath = "//a[text() = '"+ProductName+"' and @data-toggle = 'tooltip']";
        driver.assertThat().element(By.xpath(PrdocutTitleInCartXpath)).isVisible().perform();
        return this;
    }


}
