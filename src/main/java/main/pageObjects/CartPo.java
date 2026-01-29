package main.pageObjects;

import main.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class CartPo extends AbstractComponents {
    WebDriver driver;
    Actions a;

    public CartPo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.a = new Actions(driver);
    }

    @FindBy(xpath = "//*[contains(@class,'cartWrap')]") List<WebElement> cartItems;
    @FindBy(xpath = "//button[text()='Checkout']") WebElement checkout;

    By itemName = By.xpath(".//h3");

    public boolean cartItemMatch(String productName){
        cartItems.forEach(this::waitForElementToAppear);
        return cartItems.stream().anyMatch(item->item.findElement(itemName).getText().contains(productName));
    }

    public CheckoutPo clickCheckout(){
        waitForElementToAppear(checkout);
        a.moveToElement(checkout).pause(Duration.ofMillis(200)).click().perform();
        System.out.println("Checkout done");
        CheckoutPo checkoutPg = new CheckoutPo(driver);
        return checkoutPg;
    }
}
