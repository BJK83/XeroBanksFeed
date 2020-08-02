package xeroTest;

import commonFunction.BankAccountMethods;
import commonFunction.LoginMethods;

import org.testng.Assert;
import org.testng.annotations.*;

public class BanksFeedTest extends TestBase{
    static LoginMethods login=new LoginMethods();
    static BankAccountMethods bankAccounts= new BankAccountMethods();
    static String deleteAcctName;
    @BeforeClass
    public void beforeClass() throws Exception {
        login.loginXero("sowmya2709@gmail.com", "August1234");
    }

    @DataProvider(name="bankAccounts")
    public Object[][] addBankAccounts() {
        return new Object[][]{
                {"B Hiremath", "4323", "Credit Card", "NZD"}, // Account Type - Credit Card
                {"S Hiremath", "12345678900987654321", "Everyday (day-to-day)", "NZD"}, //Account Type - Everyday (day-to-day)
                {"A Hiremath", "12345678900987654322", "Loan", "NZD"}, //Account Type - Loan
                {"C Hiremath", "12345678900987654324", "Other", "NZD"}, //Account Type - Other
                {"D Hiremath", "12345678900987654325", "Term Deposit", "NZD"}, // Account Type - Term Deposit
                {"E Hiremath", "12345678900987654321", "Loan", "NZD"}, // Duplicate Bank Record
                {"F Hiremath", "12345678900987654326", "Loan", "NZD"}, //different  Account name and Account Number
                {"G Hiremath", "12345678900987654321", "Everyday (day-to-day)", "NZD"}, //Different Account Name but same Account number
                {"G Hiremath", "12345678900987654321", "Everyday (day-to-day)", "NZD"} //Different Account number but same Account Name
        };

    }


    @Test(dataProvider = "bankAccounts")
    public static void testAddBank(String accountName, String accountNumber, String accountType, String currencyType) throws Exception {
        deleteAcctName= accountName;
        bankAccounts.selectBankAccountMenu();
        bankAccounts.addButtonSelect();
        bankAccounts.addAccount(accountName, accountNumber, accountType, currencyType);
        bankAccounts.skipUploadfile(accountType);
        bankAccounts.verifyAccountDetailsOnDashboard( accountName, accountNumber);
    }

    @DataProvider(name="bankAccountsDuplicate")
    public Object[][] addBankAccountsDuplicate() {
        return new Object[][]{
                {"A Hiremath", "12345678900987654322", "Loan", "NZD"}, //Account Type - Loan - Add Duplicate Account
        };

    }

    @Test(dataProvider = "bankAccountsDuplicate")
    public static void testAddDuplicateBank(String accountName, String accountNumber, String accountType, String currencyType) throws Exception {
        deleteAcctName= accountName;
        bankAccounts.selectBankAccountMenu();
        bankAccounts.addButtonSelect();
        bankAccounts.addAccount(accountName, accountNumber, accountType, currencyType);
        bankAccounts.skipUploadfile(accountType);
        bankAccounts.verifyAccountDetailsOnDashboard(accountName, accountNumber);
        bankAccounts.selectBankAccountMenu();
        bankAccounts.addButtonSelect2ndAccount();
        boolean error = bankAccounts.addDuplicateAccount(accountName, accountNumber, accountType, currencyType);
        Assert.assertTrue(error);
    }

    @AfterMethod
    public void afterTest() throws Exception{
        bankAccounts.deleteAccount(deleteAcctName);
    }

    /*@AfterClass
    public void tearDown(){
        driver.quit();
        Reporter.log("Driver Closed After Testing");
    }*/

}