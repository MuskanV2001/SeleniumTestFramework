package test.testComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.pageObjects.LoginPo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LoginPo loginPg;

    public WebDriver initializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream file_input = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\main\\resources\\GlobalData.properties");
        prop.load(file_input);

        //Ternary if-else operator condition? true:false
        String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

        if(browser.contains("chrome")){

            ChromeOptions options = new ChromeOptions();

            WebDriverManager.chromedriver().setup();
            //To run in headless mode
            if(browser.contains("headless")){
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-infobars");
            }
            driver = new ChromeDriver(options);
        }
        else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver","C:\\Users\\Muskan\\Work\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPo launchApplication() throws IOException {
        driver = initializeDriver();
        loginPg = new LoginPo(driver);
        loginPg.gotoLogin();
        return loginPg;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
        //Read JSON to String
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        //String to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File source = ss.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, destFile);
        return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
    }
}
