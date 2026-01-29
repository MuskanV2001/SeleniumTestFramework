package main.pageObjects;

import main.abstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPo extends AbstractComponents {
    WebDriver driver;

    public ConfirmationPo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//*[text()=' Thankyou for the order. ']") WebElement confirmMsg;
    @FindBy(xpath="//tr[contains(@class,'line-item')]/td[2]//div[@class='title']" ) WebElement productName;

    public boolean confirm(){
        return confirmMsg.isDisplayed();
    }

    public boolean verifyProductDisplay(String product){
        return productName.getText().equalsIgnoreCase(product);
    }
}
