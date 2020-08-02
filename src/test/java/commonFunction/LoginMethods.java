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
        element = driver.findElement(By.xpath("//*[@data-name='navigation-menu/dashboard']"));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void logoutXero() {
    	element= driver.findElement(By.xpath("//*[@data-automationid='xrh-addon-user-iconbutton']"));
    	wait.until(ExpectedConditions.visibilityOf(element));
    	element= driver.findElement(By.xpath("//a[@data-name='user-menu/log-out']"));
    }
}
