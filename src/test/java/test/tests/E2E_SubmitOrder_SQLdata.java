package test.tests;

import main.pageObjects.CartPo;
import main.pageObjects.CheckoutPo;
import main.pageObjects.ConfirmationPo;
import main.pageObjects.ProductCataloguePo;
import main.resources.jdbcConnection;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.testComponents.BaseTest;

import java.sql.SQLException;
import java.util.HashMap;

public class E2E_SubmitOrder_SQLdata extends BaseTest {

    @Test(dataProvider = "getSQLData")
    public void E2E_SubmitOrder(HashMap<String, String> input) {

        String countryName = "India";

        ProductCataloguePo cataloguePg = loginPg.loginApplication(input.get("email"), input.get("password"));

        cataloguePg.getProductsList();
        WebElement product = cataloguePg.getProductByName(input.get("product"));
        cataloguePg.addToCart(product);

        CartPo cartPg = cataloguePg.clickCart();

        Assert.assertTrue(cartPg.cartItemMatch(input.get("product")));

        CheckoutPo checkoutPg = cartPg.clickCheckout();
        checkoutPg.selectCountry(countryName);

        ConfirmationPo confirmationPg = checkoutPg.submitOrder();
        Assert.assertTrue(confirmationPg.confirm());

        System.out.println("Order Placed Successfully");
    }

    @DataProvider
    public Object[][] getSQLData() throws SQLException {
        return jdbcConnection.retrieveData();
    }
}
