package main.abstractComponents;

import main.pageObjects.CartPo;
import main.pageObjects.OrdersPo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;
    WebDriverWait wait;
    Actions a;

    public AbstractComponents(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.a = new Actions(driver);
    }

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']") WebElement cartIcon;
    @FindBy(xpath = "//button[contains(text(), ' ORDERS')]") WebElement ordersBtn;
    @FindBy(xpath = "//button[text()=' Sign Out ']") WebElement signOutBtn;

    public void waitForElementToAppear(By findBylocator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBylocator));
    }

    public void waitForElementToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForElementToDisappear(By findBylocator){
        wait.until(ExpectedConditions.numberOfElementsToBe(findBylocator, 0));
    }

    public void waitForElementToClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public CartPo clickCart(){
        waitForElementToClick(cartIcon);
        a.moveToElement(cartIcon).pause(Duration.ofMillis(200)).click().perform();
        System.out.println("Clicked Cart");
        CartPo cartPg = new CartPo(driver);
        return cartPg;
    }

    public OrdersPo clickOrders(){
        a.moveToElement(ordersBtn).pause(Duration.ofMillis(200)).click().perform();
        System.out.println("Clicked Orders");
        OrdersPo ordersPg = new OrdersPo(driver);
        return ordersPg;
    }

    public void signOut(){
        a.moveToElement(signOutBtn).click().perform();
        System.out.println("Signed out");
    }
}
