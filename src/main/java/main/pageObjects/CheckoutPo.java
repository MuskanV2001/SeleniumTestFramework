package main.pageObjects;

import main.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CheckoutPo extends AbstractComponents {

    WebDriver driver;
    Actions a;

    public CheckoutPo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.a =  new Actions(driver);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']") WebElement countryBtn;
    @FindBy(css = ".ta-item:nth-of-type(2)") WebElement country;
    @FindBy(xpath = "//*[@class='actions']/a") WebElement placeOrder;

    By countryRes = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        a.sendKeys(countryBtn,countryName).build().perform();
        waitForElementToAppear(countryRes);
        a.moveToElement(country).pause(Duration.ofMillis(200)).click();
    }

    public ConfirmationPo submitOrder(){
        a.moveToElement(placeOrder).pause(Duration.ofMillis(200)).click().perform();
        System.out.println("Order Placed");
        ConfirmationPo confirmationPg = new ConfirmationPo(driver);
        return confirmationPg;
    }
}
