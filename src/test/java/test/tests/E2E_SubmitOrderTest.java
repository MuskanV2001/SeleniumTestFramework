package test.tests;

import main.pageObjects.*;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class E2E_SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getJsonData")
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

    //To verify that the product is displayed in the Orders page
    @Test(dependsOnMethods = {"E2E_SubmitOrder"}, dataProvider = "getData", groups = {"Purchase"})
    public void verifyOrderHistory(String email, String password, String product){
        ProductCataloguePo productCatPg = loginPg.loginApplication(email, password);
        OrdersPo ordersPg = productCatPg.clickOrders();
        Assert.assertTrue(ordersPg.verifyOrderDisplayed(product));
        System.out.println("Verified Order");
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {
                {"muskan01lko@rediffmail.com" , "Muskan@123", "ADIDAS ORIGINAL"},
                {"muskanv01lko@gmail.com", "Mv@12345678", "ZARA COAT 3"},
                {"example2001@gmail.com", "John@123", "ZARA COAT 3"}};
    }

    @DataProvider
    public Object[][] getHashMapData(){
        HashMap<String, String> dataMap1 = new HashMap<>();
        dataMap1.put("email", "muskan01lko@rediffmail.com");
        dataMap1.put("password", "Muskan@123");
        dataMap1.put("product", "ADIDAS ORIGINAL");

        HashMap<String, String> dataMap2 = new HashMap<>();
        dataMap2.put("email", "example2001@gmail.com");
        dataMap2.put("password", "John@123");
        dataMap2.put("product", "ZARA COAT 3");

        return new Object[][] {{dataMap1}, {dataMap2}};
    }

    @DataProvider
    public Object[][] getJsonData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\test\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
    }

}
