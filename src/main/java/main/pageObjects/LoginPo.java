package main.pageObjects;

import main.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPo extends AbstractComponents {

    WebDriver driver;
    Actions a;

    public LoginPo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //PageFactory creates proxy objects and initElements() initializes the web elements in 'this' page class and passes 'driver' to them
        this.a = new Actions(driver);
    }

    @FindBy(id="userEmail")
    private WebElement userEmail;
    @FindBy(css="#userPassword")
    private WebElement userPassword;
    @FindBy(name="login")
    private WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    private WebElement incorrectErrorMsg;

    public void gotoLogin(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCataloguePo loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        a.moveToElement(submit).pause(Duration.ofMillis(200)).click().perform();
        System.out.println("Logging in");
        ProductCataloguePo cataloguePg = new ProductCataloguePo(driver);
        return cataloguePg;
    }

    public String verifyIncorrectLoginError(){
        waitForElementToAppear(incorrectErrorMsg);
        String errorMsg = incorrectErrorMsg.getText();
        System.out.println("Error displayed: " + errorMsg);

        waitForElementToDisappear(incorrectErrorMsg);
        return errorMsg;
    }
}
