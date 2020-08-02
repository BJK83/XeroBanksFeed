package commonFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import xeroTest.TestBase;
import java.util.*;
public class LoginMethods extends TestBase {

    public void loginXero( String login, String password) throws Exception {
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(login);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"submitButton\"]")).click();
        element = driver.findElement(By.xpath("/html/body/div[2]/form/div/div[1]/div/div/header/div/h1"));
       // wait.until(ExpectedConditions.visibilityOf(element));
    }
}
