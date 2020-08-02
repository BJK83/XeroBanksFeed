package commonFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import xeroTest.TestBase;

public class BankAccountMethods extends TestBase {

    public static void addAccount (String accountName, String accountNumber, String accountType, String currencyType)throws Exception {
        element = driver.findElement(By.xpath("//*[@id='xui-searchfield-1018-inputEl']"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys("ANZ (NZ)");
        element = driver.findElement(By.xpath("//*[@data-automationid='searchBanksList']/ul")); 
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element= driver.findElement(By.xpath("//*[@id=\"accountname-1037-inputEl\"]"));
        element.sendKeys(accountName);
        element = driver.findElement(By.xpath("//*[@id=\"accounttype-1039-inputEl\"]"));
        element.click();
        selectAccountType(accountType);
        enterAccountNumber(accountType, accountNumber,currencyType);
        element= driver.findElement(By.xpath("//*[@id=\"common-button-submit-1015\"]"));
        element.click();
    }

    public static void skipUploadfile(String accountType) throws Exception{

        if (!accountType.contains("Other"))
        {
            element = driver.findElement(By.xpath("//*[contains(text(),'got a form')]")); 
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click(); // "I've got form" button
            element = driver.findElement(By.xpath("//*[@data-automationid ='uploadForm-uploadLaterButton']"));  
            waitTillClickable(element);
            element.click(); //"I'll do it later" button
            element = driver.findElement(By.xpath("//*[@data-automationid='uploadFormLater-goToDashboardButton']")); 
            waitTillClickable(element);
            element.click(); //"Go to Dashboard" button
        }
        else if(accountType.contains("Other"))
        {
            element= driver.findElement(By.xpath("//*[@data-name='navigation-menu/dashboard']"));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        }
    }

    public static void verifyAccountDetailsOnDashboard(String accountName, String accountNumber) throws Exception{

        //Dashboard page - Verify added Account details - Name and Account number
        element= driver.findElement(By.xpath("//*[@data-automationid='bankWidget']"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.getText().contains(accountName);
        element= driver.findElement(By.xpath("//*[@data-automationid='bankWidget']"));
        element.getText().contains(accountNumber);

    }

    public static void selectBankAccountMenu() throws Exception{
        element = driver.findElement(By.xpath("//*[@data-name='navigation-menu/accounting']"));
        element.click();
        element = driver.findElement(By.xpath("//*[@data-name='navigation-menu/accounting/bank-accounts']"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();

    }

    public static void addButtonSelect() throws Exception{
        element = driver.findElement(By.xpath("//*[@data-automationid='Add Bank Account-button']")); 
        waitTillClickable(element);
        element.click();
    }

    public static void waitTillClickable(WebElement e)
    {
        wait.until(ExpectedConditions.elementToBeClickable(e));
    }



    public static void selectAccountType(String type){

    	
    	driver.findElement(By.xpath("//LI[@role='option'][text()='"+type+"']")).click();

    	
//        switch (type){
//            case "Everyday (day-to-day)": element= driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[1]"));
//                element.click();
//                break;
//            case "Loan": element= driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[2]"));
//                element.click();
//                break;
//            case "Term Deposit": element= driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[3]"));
//                element.click();
//                break;
//            case "Credit Card":element= driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[4]"));
//                element.click();
//                break;
//            case"Other":element= driver.findElement(By.xpath("/html/body/div[4]/div/ul/li[5]"));
//                element.click();
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + type);
//        }
    }

    public static void enterAccountNumber(String type, String accountNum, String currency) throws Exception{
        if(type.contains("Credit Card")){
            element= driver.findElement(By.xpath("//*[@id=\"accountnumber-1063-inputEl\"]"));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(accountNum);
        }
        else{
            element= driver.findElement(By.xpath("//*[@id=\"accountnumber-1068-inputEl\"]"));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(accountNum);
        }
        element= driver.findElement(By.xpath("//*[@id=\"accountDetailGeneric-1065-innerCt\"]"));
        element.getText().contains(currency);
    }


    public static void deleteAccount(String accountName) throws Exception{
        //Delete the Account
        element = driver.findElement(By.xpath("//*[@data-name='navigation-menu/accounting']"));
        element.click();
        element = driver.findElement(By.xpath("//*[@data-name='navigation-menu/accounting/advanced']"));
        element.click();
		/*
		 * element =
		 * driver.findElement(By.xpath("/html/body/div[2]/header/div/h2/span"));
		 * element.getText().contains("Advanced accounting"); element=
		 * driver.findElement(By.xpath(
		 * "/html/body/div[2]/main/div[2]/ol/li[2]/a/h3/span"));
		 * element.getText().contains("Chart of accounts");
		 */
        element= driver.findElement(By.xpath("/html/body/div[2]/main/div[2]/ol/li[2]/a/h3/span"));
        element.click();
        element= driver.findElement(By.xpath("//*[@id=\"title\"]")); //Chart of accounts
        element.getText().contains("Chart of accounts");
        element= driver.findElement(By.xpath("//*[@id=\"SearchTermsText\"]"));
        element.clear();
        element.sendKeys(accountName); // enter account name
        element= driver.findElement(By.xpath("//button[@onclick]")); ///html/body/form/div/div/div[2]/div[3]/div[4]/div/div[3]/div[2]/span/button

        element.click();
        element = driver.findElement(By.xpath("//*[@id=\"WillDelete\"]"));
        element.click();
        element= driver.findElement(By.xpath("//*[@id=\"ext-gen20\"]"));
        element.click(); //Delete button;
        //Pop up window
        element = driver.findElement(By.xpath("//*[@id=\"ext-gen33\"]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.getText().contains("Delete Accounts");
        element= driver.findElement(By.xpath("//*[@id=\"popupOK\"]"));
        element.click();
        element= driver.findElement(By.xpath("//*[@id=\"notify01\"]"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.getText().contains("1 account has been deleted");
        //Back to Dashboard
        element= driver.findElement(By.xpath("//*[@data-name='navigation-menu/dashboard']"));
        element.click();
        element= driver.findElement(By.xpath("//*[@data-automationid='emptyBankAccount-button']"));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.isDisplayed();


    }

    public static boolean addDuplicateAccount (String accountName, String accountNumber, String accountType, String currencyType)throws Exception {
         element = driver.findElement(By.xpath("//*[@id=\"xui-searchfield-1018-inputEl\"]"));
        element.clear();
        element.sendKeys("ANZ (NZ)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/div[3]/ul/li")).click();
        element = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/header/h1"));
        element.getText().contains("Enter your ANZ (NZ) account details");
        element= driver.findElement(By.xpath("//*[@id=\"accountname-1037-inputEl\"]"));
        element.sendKeys(accountName);
        element = driver.findElement(By.xpath("//*[@id=\"accounttype-1039-inputEl\"]"));
        element.click();
        selectAccountType(accountType);
        enterAccountNumber(accountType, accountNumber,currencyType);
        element= driver.findElement(By.xpath("//*[@id=\"common-button-submit-1015\"]"));
        element.click();
        Thread.sleep(1000);
        element= driver.findElement(By.xpath("//*[@id=\"accountname-1037\"]"));
        boolean error= element.getText().contains("Please enter a unique Name");
        return error;
    }
}