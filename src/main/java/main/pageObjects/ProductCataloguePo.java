package main.pageObjects;

import main.abstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePo extends AbstractComponents {

    WebDriver driver;
    Actions a;

    public ProductCataloguePo(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
        this.a = new Actions(driver);
    }

    @FindBy(xpath = "//div[contains(@class,'mb-3')]")
    private List<WebElement> products;

    private final By productsBy = By.xpath("//div[contains(@class,'mb-3')]");
    private final By productNameBy = By.xpath(".//h5/b");
    private final By addToCartBy = By.xpath(".//button[text()=' Add To Cart']");
    private final By addedToCart = By.cssSelector("#toast-container");
    private final By spinner = By.cssSelector(".ngx-spinner-overlay");

    public List<WebElement> getProductsList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        return getProductsList().stream().filter(item->item.findElement(productNameBy).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addToCart(WebElement product){
        if(product!=null){
            a.moveToElement(product.findElement(addToCartBy)).click().perform();
            System.out.println("Product added to cart");
        }
        waitForElementToAppear(addedToCart);
        waitForElementToDisappear(spinner);
    }

}
