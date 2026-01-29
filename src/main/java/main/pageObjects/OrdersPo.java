package main.pageObjects;

import main.abstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPo extends AbstractComponents {

    WebDriver driver;

    public OrdersPo(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tr td:nth-child(3)") List<WebElement> itemNames;


    public boolean verifyOrderDisplayed(String productName){
        return itemNames.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
    }
}
