package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test.testComponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    public LoginPo loginPage;
    public ProductCataloguePo cataloguePg;
    public CartPo cartPg;
    public ConfirmationPo confirmationPg;


    //All the code within each step definition is fetched from the E2E_SubmitOrderTest java class file


    @Given("I landed on Ecommerce Page")
    public void I_landed_on_EcommercePage() throws IOException {
        loginPage = launchApplication();
    }

    @Given("^Logged in into application with (.+) and (.+)$")
    public void Logged_into_application(String username, String password){
        cataloguePg = loginPg.loginApplication(username, password);
    }

    @When("^I add (.+) in Cart$")
    public void I_add_product_in_Cart(String productname){
        cataloguePg.getProductsList();
        WebElement product = cataloguePg.getProductByName(productname);
        cataloguePg.addToCart(product);
        cartPg = cataloguePg.clickCart();
        Assert.assertTrue(cartPg.cartItemMatch(productname));
    }

    @And("click Checkout and submit the order")
    public void click_Checkout_Submit_Order(){
        String countryName = "India";
        CheckoutPo checkoutPg = cartPg.clickCheckout();
        checkoutPg.selectCountry(countryName);
        confirmationPg = checkoutPg.submitOrder();
    }

    @Then("confirmation message displayed on Confirmation Page")
    public void Confirmation_message_displayed(){
        Assert.assertTrue(confirmationPg.confirm());
        System.out.println("Order Placed Successfully");
        driver.quit();
    }

    @Then("Error message {string} is displayed")
    public void error_message_displayed(String errorMsg){
        Assert.assertEquals(errorMsg, loginPg.verifyIncorrectLoginError());
        driver.quit();
    }
}
