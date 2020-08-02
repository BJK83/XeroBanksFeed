package xeroTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
public class TestBase {

    protected static WebDriver driver;
    protected static WebElement element;
    protected static WebDriverWait wait;
    private static final String APP_URL = "https://login.xero.com/";

    @BeforeClass
    public static void launchApplication(){
        setChromeDriverProperty();
        driver = new ChromeDriver();
        driver.get(APP_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 60);
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }

    private static void setChromeDriverProperty(){
    	String chromeDirectory = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\chromedriver.exe";
    	System.out.println(chromeDirectory);
        System.setProperty("webdriver.chrome.driver", chromeDirectory);
    }

}
