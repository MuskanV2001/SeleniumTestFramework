package test.tests;

import main.pageObjects.CartPo;
import main.pageObjects.CheckoutPo;
import main.pageObjects.ConfirmationPo;
import main.pageObjects.ProductCataloguePo;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.testComponents.BaseTest;
import test.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
    public void validateLoginError(){

        loginPg.loginApplication("muskanv01lko@gmail.com","siman");
        Assert.assertEquals("Incorrect email password.", loginPg.verifyIncorrectLoginError());
    }

    @Test
    public void validateProductCatErrors(){
        String myProductName = "ZARA COAT 3";
        String countryName = "India";

        ProductCataloguePo cataloguePg = loginPg.loginApplication("muskan01lko@rediffmail.com", "Muskan@123");

        cataloguePg.getProductsList();
        WebElement product = cataloguePg.getProductByName(myProductName);
        cataloguePg.addToCart(product);

        CartPo cartPg = cataloguePg.clickCart();

        Assert.assertTrue(cartPg.cartItemMatch(myProductName));

        CheckoutPo checkoutPg = cartPg.clickCheckout();
        checkoutPg.selectCountry(countryName);

        ConfirmationPo confirmationPg = checkoutPg.submitOrder();
        Assert.assertTrue(confirmationPg.confirm());
        Assert.assertTrue(confirmationPg.verifyProductDisplay(myProductName));
    }

}
